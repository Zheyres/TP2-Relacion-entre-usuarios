package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modelo.Arista;
import modelo.GrafoCompleto;
import modelo.KruskalMST;
import modelo.Usuario;

import java.util.List;

public class KruskalMSTTest {

    @Test
    public void testMST() {
        // Crear usuarios
        Usuario u1 = new Usuario("A", 5, 2, 3, 1);
        Usuario u2 = new Usuario("B", 4, 2, 3, 2);
        Usuario u3 = new Usuario("C", 1, 5, 4, 3);

        List<Usuario> usuarios = List.of(u1, u2, u3);

        // Grafo completo
        GrafoCompleto grafo = new GrafoCompleto();
        grafo.agregarUsuario(u1);
        grafo.agregarUsuario(u2);
        grafo.agregarUsuario(u3);
        grafo.construirAristas();
        
        // Construir MST
        KruskalMST kruskal = new KruskalMST(grafo);
        List<Arista> mst = kruskal.getMST();
        
        for (Arista a : grafo.getAristas()) {
            System.out.println(a.getUsuario1().getNombre() + " - " + a.getUsuario2().getNombre() + " : " + a.getPeso());
        }
        for (Arista a : mst) {
            System.out.println(a.getUsuario1().getNombre() + " - " + a.getUsuario2().getNombre() + " : " + a.getPeso());
        }

        // MST debe tener n-1 aristas
        assertEquals(usuarios.size() - 1, mst.size());

        // Cada usuario debe aparecer en al menos una arista
        for (Usuario u : usuarios) {
            boolean conectado = mst.stream().anyMatch(a -> a.getUsuario1().equals(u) || a.getUsuario2().equals(u));
            assertTrue(conectado);
        }
    }
   
}
