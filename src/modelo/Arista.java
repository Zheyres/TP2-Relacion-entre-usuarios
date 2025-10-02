package modelo;

public class Arista implements Comparable<Arista> {
    private Usuario u1;
    private Usuario u2;
    private int peso;

    public Arista(Usuario u1, Usuario u2) {
        this.u1 = u1;
        this.u2 = u2;
        this.peso = u1.similaridad(u2);
    }

    public Usuario getUsuario1() { return u1; }
    public Usuario getUsuario2() { return u2; }
    public int getPeso() { return peso; }

    @Override
    public int compareTo(Arista otra) {
        return Integer.compare(this.peso, otra.peso);
    }

    @Override
    public String toString() {
        return u1.getNombre() + " - " + u2.getNombre() + " : " + peso;
    }
}
