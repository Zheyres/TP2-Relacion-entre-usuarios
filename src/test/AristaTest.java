package test;


import modelo.Arista;
import modelo.IArista;
import modelo.Usuario;

import static org.junit.Assert.assertTrue;
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
    @Test
    public void testAristaEqualsIgnoraOrden() {
        Usuario uA = new Usuario("A", 1, 1, 1, 1);
        Usuario uB = new Usuario("B", 2, 2, 2, 2);

        Arista aristaAB = new Arista(uA, uB);
        Arista aristaBA = new Arista(uB, uA);
        
        // Deberían ser iguales porque representan la misma conexión
        assertTrue("Arista (A, B) debe ser igual a Arista (B, A)", aristaAB.equals(aristaBA));
        
        // Además, deben tener el mismo hashCode si equals es true
        assertEquals(aristaAB.hashCode(), aristaBA.hashCode());
    }
}
