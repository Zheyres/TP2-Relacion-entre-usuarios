package test;

import modelo.Arista;
import modelo.Usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AristaTest {

    @Test
    public void testPeso() {
        Usuario u1 = new Usuario("Ana",5,3,4,2);
        Usuario u2 = new Usuario("Bea",3,3,4,5);
        Arista a = new Arista(u1,u2);
        assertEquals(5, a.getPeso());
    }

    @Test
    public void testCompareTo() {
        Usuario u1 = new Usuario("Ana",5,3,4,2);
        Usuario u2 = new Usuario("Bea",3,3,4,5);
        Usuario u3 = new Usuario("Caro",5,5,5,5);
        Arista a1 = new Arista(u1,u2); // peso 5
        Arista a2 = new Arista(u1,u3); // peso 6
        assertTrue(a1.compareTo(a2) < 0);
        assertTrue(a2.compareTo(a1) > 0);
        assertEquals(0, a1.compareTo(new Arista(new Usuario("X",5,3,4,2), new Usuario("Y",3,3,4,5))));
    }
}
