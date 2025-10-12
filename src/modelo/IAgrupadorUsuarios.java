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
	
	/**
	 * Calcula el promedio de simililaridad entre los usuarios del mismo grupo.
	 * @return
	 */
	double[] calcularPromedioGeneralPorGrupo();
	
	/**
	 * Calcula la simililaridad entre los usuarios de un mismo grupo por cada uno de sus atributos
	 * @return
	 * devuelve una matriz donde las filas son los grupos y las columnas son los atributos promedio.
	 */
	double[][] calcularPromedioPorAtributo();
}
