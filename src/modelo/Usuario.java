package modelo;

import java.util.Objects;

public class Usuario implements IUsuario {
    private String nombre;
    private int tango; 
    private int folclore; 
    private int rock; 
    private int urbano; 

    public Usuario(String nombre, int tango, int folclore, int rock, int urbano) {
        this.nombre = nombre;
        this.tango = tango;
        this.folclore = folclore;
        this.rock = rock;
        this.urbano = urbano;
    }

    @Override
    public String getNombre() { return nombre; }
    @Override
    public int getTango() { return tango; }
    @Override
    public int getFolclore() { return folclore; }
    @Override
    public int getRock() { return rock; }
    @Override
    public int getUrbano() { return urbano; }

    // Método para calcular la similitud con otro usuario
    @Override
    public int similaridad(Usuario otro) {
        return Math.abs(this.tango - otro.tango) +
               Math.abs(this.folclore - otro.folclore) +
               Math.abs(this.rock - otro.rock) +
               Math.abs(this.urbano - otro.urbano);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return tango == usuario.tango && folclore == usuario.folclore && rock == usuario.rock && urbano == usuario.urbano
                && Objects.equals(nombre, usuario.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, tango, folclore, rock, urbano);
    }

    @Override
    public String toString() {
        return nombre + " [T:" + tango + ", F:" + folclore + ", R:" + rock + ", U:" + urbano + "]";
    }
    
}
