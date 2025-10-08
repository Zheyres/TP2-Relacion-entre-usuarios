package modelo;

import java.util.List;

public interface IAgrupadorUsuarios {

	/**
	 * Recibe un numero entero K que indica la cantidad de grupos a formar, 
	 * este no puede ser negativo o superar al cantidad de usuarios. 
	 * @param k
	 * @return
	 */
	List<List<Usuario>> dividirEnKGrupos(int k);
	
	/**
	 * Devulve las aristas removidad en la anteriro iteracion de dividir grupos,
	 * otorga informacion adicional para la interfaz de ser requerida
	 * @return
	 */
	List<Arista> getAristasRemovidas();
	
}
