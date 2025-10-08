package test;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

// Importa tus clases reales
import modelo.Usuario; 
import modelo.Arista;
import modelo.AgrupadorUsuarios;

public class AgrupadorUsuariosTest {

    // --- Clases de Mocking y Helpers ---
    
    /**
     * Helper para crear un Mock de Usuario que devuelve una similaridad predefinida.
     * Dado que el constructor original requiere 4 ints, los usamos como placeholders.
     */
    private Usuario u(String nombre, int similaridadDeseadaConOtro) {
        // Se sobreescribe similaridad() para devolver el peso exacto requerido en el test.
        return new Usuario(nombre, 0, 0, 0, 0) {
            @Override
            public int similaridad(Usuario otro) {
                // Aquí simulamos que la similaridad con el 'otro' es el peso deseado
                // (Esta simulación asume un grafo simple donde cada usuario solo tiene
                // un par relevante en el MST para simplificar el test)
                return similaridadDeseadaConOtro;
            }
            // Agregamos un hashcode simple para asegurar el comportamiento correcto en Sets/Maps
            @Override
            public int hashCode() {
                return nombre.hashCode();
            }
            @Override
            public boolean equals(Object obj) {
                if (this == obj) return true;
                if (obj == null || getClass() != obj.getClass()) return false;
                Usuario other = (Usuario) obj;
                return Objects.equals(nombre, other.getNombre());
            }
        };
    }

    /**
     * Helper para crear la Arista. Ahora solo requiere los Usuarios, el peso se calculará internamente.
     */
    private Arista a(Usuario u1, Usuario u2) {
        return new Arista(u1, u2); 
    }

    /**
     * Convierte los grupos de Usuarios a un conjunto de conjuntos de nombres para verificación.
     */
    private Set<Set<String>> obtenerConjuntosDeNombres(List<List<Usuario>> grupos) {
        Set<Set<String>> resultados = new HashSet<>();
        for (List<Usuario> grupo : grupos) {
            Set<String> nombres = new HashSet<>();
            for (Usuario u : grupo) {
                nombres.add(u.getNombre()); 
            }
            resultados.add(nombres);
        }
        return resultados;
    }

// -------------------------------------------------------------------------------------------------
// Caso 1: División de un Árbol Lineal en Dos Grupos Iguales

    @Test
    void testDivisionEnDosGrupos() {
        // Grafo MST: U1 --(1)--> U2 --(10)--> U3 --(1)--> U4
        // Queremos que el peso de la arista (U2, U3) sea 10 (la mayor).
        
        // Creamos Usuarios con la similaridad "simulada" que necesitamos para el MST:
        Usuario u1 = u("Alice", 1); // similaridad U1-U2 = 1
        Usuario u2 = u("Bob", 10);  // similaridad U2-U3 = 10
        Usuario u3 = u("Charlie", 1); // similaridad U3-U4 = 1
        Usuario u4 = u("David", 0);  // No necesita definir similaridad de salida

        List<Arista> mst = Arrays.asList(
            a(u1, u2), // Peso será 1 (o el valor que devuelva u1.similaridad(u2))
            a(u2, u3), // Peso será 10 
            a(u3, u4)  // Peso será 1
        );

        // Ajustamos la verificación: necesitamos asegurarnos de que los pesos se calculen.
        // Si el MST es {u1-u2, u2-u3, u3-u4}, sus pesos serán: 1, 10, 1. La mayor es 10.

        AgrupadorUsuarios agrupador = new AgrupadorUsuarios(mst);
        List<List<Usuario>> grupos = agrupador.dividirEnKGrupos(2);
        System.out.println(grupos);
        assertEquals(2, grupos.size(), "Debería dividirse en 2 grupos al remover la arista más pesada (peso 10).");

        Set<Set<String>> esperado = Set.of(
            Set.of("Alice", "Bob"),
            Set.of("Charlie", "David")
        );
        
        Set<Set<String>> actual = obtenerConjuntosDeNombres(grupos);
        assertEquals(esperado, actual, "Los grupos formados no coinciden con la división esperada.");
    }

// -------------------------------------------------------------------------------------------------
// Caso 2: Identificación de un Usuario Aislado (Outlier)

    @Test
    void testUsuarioAislado() {
        // Grafo MST: U1 --(10)--> U2 --(100)--> U3
        // Arista más pesada (removida): (U2, U3) con peso 100.
        // Resultado esperado: {U1, U2} y {U3}

        Usuario u1 = u("GrupoA", 10);
        Usuario u2 = u("GrupoB", 100);
        Usuario u3 = u("OutlierC", 0); 

        List<Arista> mst = Arrays.asList(
            a(u1, u2), // Peso 10
            a(u2, u3)  // Peso 100
        );

        AgrupadorUsuarios agrupador = new AgrupadorUsuarios(mst);
        List<List<Usuario>> grupos = agrupador.dividirEnKGrupos(2);

        assertEquals(2, grupos.size(), "Debería dividirse en 2 grupos (uno de tamaño 2 y uno de tamaño 1).");

        Set<Set<String>> esperado = Set.of(
            Set.of("GrupoA", "GrupoB"),
            Set.of("OutlierC")
        );
        
        Set<Set<String>> actual = obtenerConjuntosDeNombres(grupos);
        assertEquals(esperado, actual, "El usuario aislado no fue detectado correctamente.");
    }

// -------------------------------------------------------------------------------------------------
// Caso 3: Grafo Mínimo (Dos Nodos) Dividido en Dos Componentes Aisladas

    @Test
    void testGrafoMinimoDividido() {
        // Grafo MST: U1 --(5)--> U2
        // Arista más pesada (removida): (U1, U2) con peso 5.
        // Resultado esperado: {U1} y {U2}

        Usuario u1 = u("Solo1", 5);
        Usuario u2 = u("Solo2", 0);

        List<Arista> mst = Arrays.asList(
            a(u1, u2) // Peso 5
        );

        AgrupadorUsuarios agrupador = new AgrupadorUsuarios(mst);
        List<List<Usuario>> grupos = agrupador.dividirEnKGrupos(2);

        assertEquals(2, grupos.size(), "La única arista debe ser removida, creando 2 componentes aisladas.");

        Set<Set<String>> esperado = Set.of(
            Set.of("Solo1"),
            Set.of("Solo2")
        );
        
        Set<Set<String>> actual = obtenerConjuntosDeNombres(grupos);
        assertEquals(esperado, actual, "El grafo mínimo no se dividió correctamente en 2 componentes aisladas.");
    }
}