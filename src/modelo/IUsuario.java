package modelo;

public interface IUsuario {
	
	
	/**
	 * Metodo que calcula la similutud entre usuarios usando la 
	 * sumatoria de los gustos musicales , utiliza el valor absoluto de los resultados.
	 * @param
	 */
	int similaridad(Usuario otro);
	
	
	
	//Getters
	String getNombre();
	    int getT();
	  	int getF();
	    int getR(); 
	    int getU();

}
