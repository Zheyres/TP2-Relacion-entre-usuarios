package modelo;

import java.util.ArrayList;
import java.util.List;

public class GrafoCompleto {
    private List<Usuario> vertices;
    private List<Arista> aristas;

    public GrafoCompleto() {
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    public void agregarUsuario(Usuario u) {
        vertices.add(u);
    }

    public List<Usuario> getVertices() { return vertices; }
    public List<Arista> getAristas() { return aristas; }

    public void construirAristas() {
        aristas.clear();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                aristas.add(new Arista(vertices.get(i), vertices.get(j)));
            }
        }
    }
}
