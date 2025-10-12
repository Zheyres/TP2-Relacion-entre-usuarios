package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;

import modelo.IGrafoCompleto;
import modelo.AgrupadorUsuarios;
import modelo.GrafoCompleto;
import modelo.IAgrupadorUsuarios;
import modelo.IKruskalMST;
import modelo.KruskalMST;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;

public class frmCargaDeDatos {

	private JFrame frmBuscadorDeSimilaridades;
	private JButton btnMostrarMST;
	private JSpinner spinnerCantidadDeGrupos;
	private JLabel lblNewLabel;
	private JTextPane textPane;
	private JButton btnMostrarGrupos;

	private IAgrupadorUsuarios agrupador;
	private IKruskalMST kruskal;
	private IGrafoCompleto grafo;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmCargaDeDatos window = new frmCargaDeDatos();
					window.frmBuscadorDeSimilaridades.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
		    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            UIManager.setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		} catch (Exception e) {
		    // Si Nimbus no está disponible, se usa el look and feel por defecto
		    e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public frmCargaDeDatos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBuscadorDeSimilaridades = new JFrame();
		frmBuscadorDeSimilaridades.setBackground(new Color(192, 192, 192));
		frmBuscadorDeSimilaridades.getContentPane().setBackground(new Color(135, 206, 250));
		frmBuscadorDeSimilaridades.setTitle("Buscador de Similaridades V1.0");
		frmBuscadorDeSimilaridades.setBounds(100, 100, 480, 420);
		frmBuscadorDeSimilaridades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBuscadorDeSimilaridades.getContentPane().setLayout(null);

		
		this.grafo = new GrafoCompleto();
		
		this.kruskal = new KruskalMST((GrafoCompleto)grafo);
		
		this.agrupador = new AgrupadorUsuarios(kruskal.getMST());
		
		
		btnMostrarMST = new JButton("Mostrar usuarios");
		btnMostrarMST.setBackground(new Color(244, 164, 96));
		btnMostrarMST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				textPane.setText(grafo.imprimirUsuarios());
				
			}
		});
		btnMostrarMST.setBounds(10, 68, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnMostrarMST);
		
		spinnerCantidadDeGrupos = new JSpinner();
		spinnerCantidadDeGrupos.setBackground(new Color(211, 211, 211));
		spinnerCantidadDeGrupos.setModel(new SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(2), null, Integer.valueOf(1)));
		spinnerCantidadDeGrupos.setBounds(10, 179, 50, 30);
		frmBuscadorDeSimilaridades.getContentPane().add(spinnerCantidadDeGrupos);
		
		lblNewLabel = new JLabel("Cantidad de grupos:");
		lblNewLabel.setBounds(10, 154, 134, 14);
		frmBuscadorDeSimilaridades.getContentPane().add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		textPane.setBackground(new Color(248, 248, 255));
		textPane.setEditable(false);
		textPane.setBounds(169, 11, 285, 359);
		frmBuscadorDeSimilaridades.getContentPane().add(textPane);
		
		btnMostrarGrupos = new JButton("Mostrar grupos");
		btnMostrarGrupos.setBackground(new Color(123, 104, 238));
		btnMostrarGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TextPaneManager.recargarPanelDeGrupos(grafo, (int)spinnerCantidadDeGrupos.getValue(), textPane);
				
			}
		});
		btnMostrarGrupos.setBounds(10, 111, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnMostrarGrupos);
		

		JButton btnRegistrar = new JButton("Registrar usuario");
		btnRegistrar.setBackground(new Color(127, 255, 0));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmRegistrarUsuario.crear(grafo, textPane, (int)spinnerCantidadDeGrupos.getValue());
				
			}
		});
		btnRegistrar.setBounds(10, 25, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnRegistrar);
		
		JButton btnNewButton = new JButton("Promedio Gustos");
		btnNewButton.setBackground(new Color(222, 184, 135));
		btnNewButton.setBounds(10, 222, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnNewButton);
		
	}
}
