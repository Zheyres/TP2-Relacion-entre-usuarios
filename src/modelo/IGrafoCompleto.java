package modelo;

import java.util.List;

public interface IGrafoCompleto {
	
	/**
     * Agrega un usuario a la lista como otro vertice del grafo.
     * @param
     */
	void agregarUsuario(Usuario u);
	
	/**
     * Genera las aristas de un grafo entre 2 usuarios como vertices.
     */
	void construirAristas();
	
	/**
     * Devuelve una lista con todos los vertices (Usuarios) del grafo.
     */
	List<Usuario> getUsuarios();
	/**
     * Devuelve una lista con todos as aristas U1-U2.
     */
	List<Arista> getAristas();
}
