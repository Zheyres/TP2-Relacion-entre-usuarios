package modelo;

import java.util.*;

public class KruskalMST implements IKruskalMST {
    private List<Arista> mst;

    public KruskalMST(GrafoCompleto grafo) {
        this.mst = new ArrayList<>();

        // obtener todas las aristas
        List<Arista> aristas = new ArrayList<>(grafo.getAristas());

        // ordenar por peso (ascendente)
        Collections.sort(aristas, Comparator.comparingDouble(Arista::getPeso));

        // inicializar UnionFind
        UnionFind uf = new UnionFind(grafo.getUsuarios().size());

        // recorrer aristas
        for (Arista arista : aristas) {
            int u = grafo.getUsuarios().indexOf(arista.getUsuario1());
            int v = grafo.getUsuarios().indexOf(arista.getUsuario2());

            if (uf.union(u, v)) {
                mst.add(arista);
            }
        }
    }
    
    @Override
    public List<Arista> getMST() {
        return mst;
    }
    
    @Override
    public String toString() {
    	
    	return mst.toString();
    	
    }
}
