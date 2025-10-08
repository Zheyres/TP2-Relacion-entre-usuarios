package test;

import modelo.GrafoCompleto;
import modelo.Usuario;
import modelo.Arista;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GrafoCompletoTest {

    @Test
    public void testConstruirAristas() {
        GrafoCompleto g = new GrafoCompleto();
        Usuario u1 = new Usuario("Ana",5,3,4,2);
        Usuario u2 = new Usuario("Bea",3,3,4,5);
        Usuario u3 = new Usuario("Caro",5,5,5,5);
        g.agregarUsuario(u1);
        g.agregarUsuario(u2);
        g.agregarUsuario(u3);
        g.construirAristas();
        List<Arista> aristas = g.getAristas();
        
        // Para 3 nodos, debe haber 3 aristas
        assertEquals(3, aristas.size());
    }
}
