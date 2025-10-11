package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

public class frmCargaDeDatos {

	private JFrame frame;
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
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 480, 420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		
		this.grafo = new GrafoCompleto();
		
		this.kruskal = new KruskalMST((GrafoCompleto)grafo);
		
		this.agrupador = new AgrupadorUsuarios(kruskal.getMST());
		
		
		btnMostrarMST = new JButton("Mostrar usuarios");
		btnMostrarMST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				textPane.setText(grafo.imprimirUsuarios());
				
			}
		});
		btnMostrarMST.setBounds(10, 68, 149, 32);
		frame.getContentPane().add(btnMostrarMST);
		
		spinnerCantidadDeGrupos = new JSpinner();
		spinnerCantidadDeGrupos.setModel(new SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(2), null, Integer.valueOf(1)));
		spinnerCantidadDeGrupos.setBounds(10, 179, 30, 20);
		frame.getContentPane().add(spinnerCantidadDeGrupos);
		
		lblNewLabel = new JLabel("Cantidad de grupos:");
		lblNewLabel.setBounds(10, 154, 134, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setBounds(169, 11, 285, 359);
		frame.getContentPane().add(textPane);
		
		btnMostrarGrupos = new JButton("Mostrar grupos");
		btnMostrarGrupos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TextPaneManager.recargarPanelDeGrupos(grafo, (int)spinnerCantidadDeGrupos.getValue(), textPane);
				
			}
		});
		btnMostrarGrupos.setBounds(10, 111, 149, 32);
		frame.getContentPane().add(btnMostrarGrupos);
		

		JButton btnRegistrar = new JButton("Registrar usuario");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmRegistrarUsuario.crear(grafo, textPane, (int)spinnerCantidadDeGrupos.getValue());
				
			}
		});
		btnRegistrar.setBounds(10, 25, 149, 32);
		frame.getContentPane().add(btnRegistrar);
		
	}
}
