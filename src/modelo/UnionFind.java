package modelo;
public class UnionFind implements IUnionFind {
   
	private int[] parent;
    private int[] rank;
    
    
    public UnionFind(int n) {  // "n" es el tamano del grafo, la cantidad de vertices/usuarios
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    @Override
    public int find(int x) { //X es el conjunto donde pertenece ademas del indice del vertice en la lista
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }
    
    @Override
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false; // ya están conectados

        // unión por rango , el mas chico se uni al grupo mas grande.
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }
}
