package vista;

import java.util.List;

import javax.swing.JTextPane;

import modelo.AgrupadorUsuarios;
import modelo.GrafoCompleto;
import modelo.IAgrupadorUsuarios;
import modelo.IGrafoCompleto;
import modelo.IKruskalMST;
import modelo.KruskalMST;
import modelo.Usuario;

public class TextPaneManager {
	
	public static void recargarPanelDeGrupos(IGrafoCompleto grafo, int cantidadDeGrupos, JTextPane panel) {

		if(grafo.getUsuarios().size() < 2)
			return;
		
		IKruskalMST kruskal = new KruskalMST((GrafoCompleto)grafo);

		IAgrupadorUsuarios agrupador = new AgrupadorUsuarios(kruskal.getMST());
		
		StringBuilder sb = new StringBuilder();
		int numeroDeGrupo = 1;
		for(List<Usuario> lista : agrupador.dividirEnKGrupos(cantidadDeGrupos)) {
			
			sb.append("Grupo " + numeroDeGrupo);
			sb.append("\n");
			sb.append("\n");
			
			for(Usuario user : lista) {
				
				sb.append(user.toString());
				sb.append("\n");
				
			}
			
			sb.append("\n");
			sb.append("\n");
			numeroDeGrupo++;
			
		}
		sb.append(mostrarPromedios(agrupador,panel));
		panel.setText(sb.toString());
		
	}
	private static String mostrarPromedios(IAgrupadorUsuarios agrupador,JTextPane panel) {
	    
		StringBuilder sb = new StringBuilder();

	    double[] generales = agrupador.calcularPromedioGeneralPorGrupo();
	    double[][] atributos = agrupador.calcularPromedioPorAtributo();

	    for (int i = 0; i < generales.length; i++) {
	        sb.append("🎵 Grupo ").append(i + 1).append(":\n");
	        sb.append("  Promedio general: ").append(String.format("%.2f", generales[i])).append("\n");
	        sb.append("  Tango: ").append(String.format("%.2f", atributos[i][0])).append("\n");
	        sb.append("  Rock: ").append(String.format("%.2f", atributos[i][1])).append("\n");
	        sb.append("  Urbano: ").append(String.format("%.2f", atributos[i][2])).append("\n");
	        sb.append("  Folclore: ").append(String.format("%.2f", atributos[i][3])).append("\n\n");
	    }

	    return sb.toString();
	}
	public static void limpiarTextPane(JTextPane textPane) {
	    textPane.setText("");
	}


}