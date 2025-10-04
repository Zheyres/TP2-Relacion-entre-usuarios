package modelo;

import java.util.*;

public class AgrupadorUsuarios {
    
    
    private List<Arista> mst;
    
    //para no dejar afuera los aislados
    private Set<Usuario> todosLosUsuarios = new HashSet<>(); 
    
    // Mapa de adyacencia: esencial para que el DFS sea eficiente (O(V+E)).
    private Map<Usuario, List<Usuario>> adyacencia; 
    
    public AgrupadorUsuarios(List<Arista> mst) {
        
        this.mst = new ArrayList<>(mst);
        
        inicializarUsuarios();
    }

    
    private void inicializarUsuarios() {
        for (Arista a : mst) {
            todosLosUsuarios.add(a.getUsuario1());
            todosLosUsuarios.add(a.getUsuario2());
        }
    }
    
    
    
    public List<List<Usuario>> dividirEnGrupos() {
        
        Arista aristaMasPesada = encontrarAristaMasPesada();
        if (aristaMasPesada != null) {
            mst.remove(aristaMasPesada);
        }
        
        construirAdyacencia(); 
        return encontrarComponentes(); 
    }
 
    private Arista encontrarAristaMasPesada() {
        return mst.stream().max(Comparator.comparingInt(Arista::getPeso)).orElse(null);
    }
    
    /**
     * Construye un mapa de adyacencia a partir de las aristas restantes (O(V+E)).
     * Esto transforma la lista de aristas plana en una estructura optimizada para el DFS.
     */
    private void construirAdyacencia() {
        adyacencia = new HashMap<>();
        
        
        for (Usuario u : todosLosUsuarios) {
            adyacencia.put(u, new ArrayList<>());
        }

        
        for (Arista a : mst) {
            Usuario u1 = a.getUsuario1();
            Usuario u2 = a.getUsuario2();
            
            // Grafo no dirigido: agregar ambas direcciones
            adyacencia.get(u1).add(u2);
            adyacencia.get(u2).add(u1);
        }
    }

    /**
     * Itera sobre todos los usuarios y usa DFS para encontrar las componentes conexas.
     */
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

        return componentes;
    }

    /**
     * Realiza una Búsqueda en Profundidad (DFS) eficiente.
     * Complejidad: O(V + E) para la componente conexa actual.
     */
    private void dfs(Usuario actual, List<Usuario> grupo, Set<Usuario> visitados, Map<Usuario, List<Usuario>> adyacencia) {
        visitados.add(actual);
        grupo.add(actual);

        
        for (Usuario vecino : adyacencia.get(actual)) { 
            if (!visitados.contains(vecino)) {
                
                dfs(vecino, grupo, visitados, adyacencia);
            }
        }
    }
}
