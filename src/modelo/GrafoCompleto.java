package modelo;

import java.util.ArrayList;
import java.util.List;

public class GrafoCompleto implements IGrafoCompleto {
    
	private List<Usuario> vertices;
    private List<Arista> aristas;

    public GrafoCompleto() {
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    @Override
    public void agregarUsuario(Usuario u) {
        vertices.add(u);
    }
    @Override
    public void construirAristas() {
        aristas.clear();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) { // j = i+1 evita duplicados y C-C
                Usuario u1 = vertices.get(i);
                Usuario u2 = vertices.get(j);
                aristas.add(new Arista(u1, u2));
            }
            
            System.out.print(aristas.toString() + "\n");
        }
    }
    
    @Override
	public boolean aristasMasDeDos() {
		if(aristas.size()<2) {return false;}
		return true;
	}
    
    @Override
    public List<Usuario> getUsuarios() { return vertices; }
	
    @Override
    public List<Arista> getAristas() { return aristas; }
	
}
