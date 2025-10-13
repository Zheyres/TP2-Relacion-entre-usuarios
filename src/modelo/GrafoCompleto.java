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
        if (u != null && !vertices.contains(u)) {
            vertices.add(u);
        }
    }

    @Override
    public void construirAristas() {
        aristas.clear();
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                Usuario u1 = vertices.get(i);
                Usuario u2 = vertices.get(j);
                aristas.add(new Arista(u1, u2));
            }
        }
    }

    @Override
    public boolean aristasMasDeDos() {
        return aristas.size() >= 2;
    }

    @Override
    public List<Usuario> getUsuarios() {
        // Devuelve una copia de la lista de vértices para evitar modificaciones externas
        return new ArrayList<>(vertices);
    }

    @Override
    public List<Arista> getAristas() {
        return aristas;
    }

    @Override
    public String imprimirUsuarios() {
        StringBuilder sb = new StringBuilder();
        for (Usuario user : vertices) {
            sb.append(user.toString()).append("\n");
        }
        return sb.toString();
    }

    public void eliminarUsuario(Usuario u) {
        if (u == null) return;
        vertices.remove(u);
        aristas.removeIf(a -> a.getUsuario1().equals(u) || a.getUsuario2().equals(u));
    }

    @Override
    public String toString() {
        return vertices.toString() + " --- " + aristas.toString();
    }
}
