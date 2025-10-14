package modelo;

public interface IUsuario {
	
	
	/**
	 * Metodo que calcula la similutud entre usuarios usando la 
	 * sumatoria de los gustos musicales , utiliza el valor absoluto de los resultados.
	 * @param
	 */
	int similaridad(Usuario otro);
	
	String getNombre();
	
	int getTango();
	int getFolclore();
	int getRock(); 
	int getUrbano();

}
