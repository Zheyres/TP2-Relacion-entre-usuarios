package modelo;

public class Arista implements IArista, Comparable<Arista> {
    private Usuario u1;
    private Usuario u2;
    private int peso;

    public Arista(Usuario u1, Usuario u2) {
        this.u1 = u1;
        this.u2 = u2;
        this.peso = u1.similaridad(u2);
    }
    @Override
    public Usuario getUsuario1() { return u1; }
    @Override
    public Usuario getUsuario2() { return u2; }
    @Override 
    public int getPeso() { return peso; }

    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }

    @Override
    public String toString() {
        return u1.getNombre() + " - " + u2.getNombre() + " : " + peso;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Arista arista = (Arista) o;
        
        // Comparamos si (this.u1 == arista.u1 Y this.u2 == arista.u2) 
        // O si (this.u1 == arista.u2 Y this.u2 == arista.u1)
        boolean ordenDirecto = this.u1.equals(arista.u1) && this.u2.equals(arista.u2);
        boolean ordenInverso = this.u1.equals(arista.u2) && this.u2.equals(arista.u1);

        return ordenDirecto || ordenInverso;
    }
    @Override
    public int hashCode() {
        // La suma de los hashes es conmutativa: hash(u1) + hash(u2) = hash(u2) + hash(u1)
        return u1.hashCode() + u2.hashCode(); 
    }
}
