package test;

import modelo.Usuario;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

    @Test
    public void testSimilaridad() {
        Usuario u1 = new Usuario("Ana", 5, 3, 4, 2);
        Usuario u2 = new Usuario("Bea", 3, 3, 4, 5);
        int esperado = Math.abs(5-3) + Math.abs(3-3) + Math.abs(4-4) + Math.abs(2-5); // 2+0+0+3=5
        assertEquals(esperado, u1.similaridad(u2));
    }

    @Test
    public void testEquals() {
        Usuario u1 = new Usuario("Ana", 5,3,4,2);
        Usuario u2 = new Usuario("Ana", 5,3,4,2);
        assertEquals(u1, u2);
    }
    @Test
    public void testNotEquals() {
        Usuario u1 = new Usuario("Ana", 5,3,4,2);
        Usuario u3 = new Usuario("Ana", 4,3,4,2);
        assertNotEquals(u1, u3);
    }
}
