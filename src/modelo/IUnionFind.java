package modelo;

public interface IUnionFind {
	
	/**
	 * encuentra el vertice pariente para hacer la union
	 * @param x
	 * @return
	 */
	int find(int x);
	
	/**
	 * Hace la union de x mas chica a y mas grande componentes conexas
	 * @param x
	 * @param y
	 * @return
	 */
	boolean union(int x, int y);
	
}
