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
		
		panel.setText(sb.toString());
	}
}
