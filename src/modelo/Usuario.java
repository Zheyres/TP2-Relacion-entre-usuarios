package modelo;

import java.util.Objects;

public class Usuario {
    private String nombre;
    private int t; // tango
    private int f; // folclore
    private int r; // rock
    private int u; // urbano

    public Usuario(String nombre, int t, int f, int r, int u) {
        this.nombre = nombre;
        this.t = t;
        this.f = f;
        this.r = r;
        this.u = u;
    }

    public String getNombre() { return nombre; }
    public int getT() { return t; }
    public int getF() { return f; }
    public int getR() { return r; }
    public int getU() { return u; }

    // Método para calcular la similitud con otro usuario
    public int similaridad(Usuario otro) {
        return Math.abs(this.t - otro.t) +
               Math.abs(this.f - otro.f) +
               Math.abs(this.r - otro.r) +
               Math.abs(this.u - otro.u);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return t == usuario.t && f == usuario.f && r == usuario.r && u == usuario.u
                && Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, t, f, r, u);
    }

    @Override
    public String toString() {
        return nombre + " [T:" + t + ", F:" + f + ", R:" + r + ", U:" + u + "]";
    }
}
