package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import modelo.Usuario;

public class HashCodeTest {
	 @Test
	    public void testEqualsYHashCode() {
	        Usuario u1 = new Usuario("A", 5, 2, 3, 1);
	        Usuario u2 = new Usuario("A", 5, 2, 3, 1);
	        Usuario u3 = new Usuario("B", 5, 2, 3, 1);

	        // equals
	        assertTrue(u1.equals(u2));
	        assertFalse(u1.equals(u3));

	        // hashCode
	        assertEquals(u1.hashCode(), u2.hashCode());
	        assertNotEquals(u1.hashCode(), u3.hashCode());
	    }
}
