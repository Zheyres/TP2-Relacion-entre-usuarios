package modelo;

import java.util.*;

public class AgrupadorUsuarios implements IAgrupadorUsuarios {
    
    
    private final List<Arista> mstOriginal; 
    
    private List<Arista> aristasRemovidas; 
    
    // Conjunto de trabajo para las aristas restantes (se redefine en cada llamada).
    private List<Arista> mstTrabajo;
    
    private final Set<Usuario> todosLosUsuarios = new HashSet<>(); 
    
    private Map<Usuario, List<Usuario>> adyacencia; 
    private List<List<Usuario>> gruposGenerados;
    
    
    public AgrupadorUsuarios(List<Arista> mst) {
        this.mstOriginal = new ArrayList<>(mst);
        
        this.aristasRemovidas = new ArrayList<>(); 
        inicializarUsuarios();
    }

    private void inicializarUsuarios() {
        for (Arista a : mstOriginal) {
            todosLosUsuarios.add(a.getUsuario1());
            todosLosUsuarios.add(a.getUsuario2());
        }
    }
    

    
    @Override
    public List<Arista> getAristasRemovidas() {
        
        return new ArrayList<>(aristasRemovidas); 
    }
    
    // --- MÉTODO PRINCIPAL ---

    /**
     * Divide el conjunto de usuarios en 'k' grupos removiendo las k-1 aristas más pesadas.
     * @param k El número de grupos deseados (1 < k <= |V|).
     * @return Una lista de listas de Usuarios, donde cada lista interior es un grupo.
     */
    @Override
    public List<List<Usuario>> dividirEnKGrupos(int k) {
        int maxGrupos = todosLosUsuarios.size();
        
        if (k <= 1 || k > maxGrupos) {k=maxGrupos;}

        //  Obtener y ordenar las aristas
        List<Arista> aristasOrdenadas = new ArrayList<>(this.mstOriginal); 
        aristasOrdenadas.sort(Comparator.comparingInt(Arista::getPeso).reversed());

       
        int numAristasARemover = k - 1;
        
        if (aristasOrdenadas.size() > numAristasARemover) {
            
            this.aristasRemovidas = aristasOrdenadas.subList(0, numAristasARemover);
           
            this.mstTrabajo = aristasOrdenadas.subList(numAristasARemover, aristasOrdenadas.size());
        } else {
            // Caso borde: se remueven todas las aristas
            this.aristasRemovidas = aristasOrdenadas;
            this.mstTrabajo = new ArrayList<>();
        }

        //  Configurar el grafo de trabajo y encontrar las componentes
        construirAdyacencia(); 
        List<List<Usuario>> grupos = encontrarComponentes(); 
        
        
        return grupos; 
    }
 
    // --- MÉTODOS PRIVADOS AUXILIARES  ---

    private void construirAdyacencia() {
        adyacencia = new HashMap<>();
        
        for (Usuario u : todosLosUsuarios) {
            adyacencia.put(u, new ArrayList<>());
        }

        for (Arista a : mstTrabajo) {
            Usuario u1 = a.getUsuario1();
            Usuario u2 = a.getUsuario2();
            adyacencia.get(u1).add(u2);
            adyacencia.get(u2).add(u1);
        }
    }

    private List<List<Usuario>> encontrarComponentes() {
        Set<Usuario> visitados = new HashSet<>();
        List<List<Usuario>> componentes = new ArrayList<>();

        for (Usuario u : todosLosUsuarios) { 
            if (!visitados.contains(u)) {
                List<Usuario> grupo = new ArrayList<>();
                dfs(u, grupo, visitados, adyacencia); 
                componentes.add(grupo);
            }
        }
        this.gruposGenerados=componentes;
        return componentes;
    }

    private void dfs(Usuario actual, List<Usuario> grupo, Set<Usuario> visitados, Map<Usuario, List<Usuario>> adyacencia) {
        visitados.add(actual);
        grupo.add(actual);

        for (Usuario vecino : adyacencia.get(actual)) { 
            if (!visitados.contains(vecino)) {
                dfs(vecino, grupo, visitados, adyacencia);
            }
        }
    }
    @Override
    public double[] calcularPromedioGeneralPorGrupo() {
        if(gruposGenerados==null) {}
    	double[] promedios = new double[this.gruposGenerados.size()];

        for (int i = 0; i < this.gruposGenerados.size(); i++) {
            List<Usuario> grupo = this.gruposGenerados.get(i);
            double suma = 0;
            int cantidad = grupo.size() * 4; // 4 atributos
            for (Usuario u : grupo) {
                suma += u.getTango() + u.getRock() + u.getUrbano() + u.getFolclore();
            }
            promedios[i] = suma / cantidad;
        }
        return promedios;
    }
    
    @Override
    public double[][] calcularPromedioPorAtributo() {
    	if(gruposGenerados==null) {}
    	// filas = grupos, columnas = atributos
        double[][] promedios = new double[this.gruposGenerados.size()][4];

        for (int i = 0; i < this.gruposGenerados.size(); i++) {
            List<Usuario> grupo = this.gruposGenerados.get(i);
            double sumaTango = 0, sumaRock = 0, sumaUrbano = 0, sumaFolclore = 0;

            for (Usuario u : grupo) {
                sumaTango += u.getTango();
                sumaRock += u.getRock();
                sumaUrbano += u.getUrbano();
                sumaFolclore += u.getFolclore();
            }

            int n = grupo.size();
            promedios[i][0] = sumaTango / n;
            promedios[i][1] = sumaRock / n;
            promedios[i][2] = sumaUrbano / n;
            promedios[i][3] = sumaFolclore / n;
        }

        return promedios;
    }
    
}