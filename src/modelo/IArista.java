package modelo;

public interface IArista {

    /**
     * Devuelve el primer Usuario conectado por esta arista.
     * @return El primer Usuario.
     */
    Usuario getUsuario1();

    /**
     * Devuelve el segundo Usuario conectado por esta arista.
     * @return El segundo Usuario.
     */
    Usuario getUsuario2();

    /**
     * Devuelve el peso de la arista, que representa la similaridad
     * entre los dos Usuarios.
     * @return El valor del peso (similaridad).
     */
    int getPeso();
    
}