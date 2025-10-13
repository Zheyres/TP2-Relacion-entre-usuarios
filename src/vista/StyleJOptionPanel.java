package vista;

import java.awt.Color;

import javax.swing.UIManager;

public class StyleJOptionPanel {

	
	public static void StyleJOptionPannel() {
		 try {
	            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        // Colores principales (tema claro)
	        Color fondoPrincipal = new Color(105, 206, 255); // cyan
	        Color fondoCampo = Color.WHITE;
	        Color textoNormal = Color.BLACK;
	        Color botonFondo = new Color(244, 164, 96); // naranja suave
	        Color botonTexto = Color.BLACK;

	        // --- Ajustes globales de Nimbus ---
	        UIManager.put("control", fondoPrincipal);           // color general de fondo
	        UIManager.put("nimbusLightBackground", fondoCampo); // fondos de textfields
	        UIManager.put("text", textoNormal);                 // texto general
	        UIManager.put("infoText", textoNormal);
	        UIManager.put("OptionPane.background", fondoPrincipal);
	        UIManager.put("Panel.background", fondoPrincipal);
	        UIManager.put("OptionPane.messageForeground", textoNormal);

	        // Botones
	        UIManager.put("Button.background", botonFondo);
	        UIManager.put("Button.foreground", botonTexto);
	        UIManager.put("Button[Enabled].backgroundPainter", null);
	        UIManager.put("Button[Focused].backgroundPainter", null);
	        UIManager.put("Button[Default].backgroundPainter", null);

	        // Campos de texto
	        UIManager.put("TextField.background", fondoCampo);
	        UIManager.put("TextField.foreground", textoNormal);
	        UIManager.put("TextField.caretForeground", textoNormal);
	    }
}
