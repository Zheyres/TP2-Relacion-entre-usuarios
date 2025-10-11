package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import modelo.AgrupadorUsuarios;
import modelo.IAgrupadorUsuarios;
import modelo.GrafoCompleto;
import modelo.IGrafoCompleto;
import modelo.IKruskalMST;
import modelo.KruskalMST;
import modelo.Usuario;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Color;

public class frmRegistrarUsuario {

	private JFrame frmRegistrarUsuario;
	private JTextField txtNombre;
	private IGrafoCompleto grafo;
	private IKruskalMST kruskal;
	private IAgrupadorUsuarios agrupador;
	private JTextPane textPane;
	private int cantidadDeGrupos;
	/**
	 * Launch the application.
	 */
	public static void crear(IGrafoCompleto grafoCompleto, JTextPane textPane, int cantidadDeGrupos) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRegistrarUsuario window = new frmRegistrarUsuario(grafoCompleto, textPane, cantidadDeGrupos);
					window.frmRegistrarUsuario.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public frmRegistrarUsuario(IGrafoCompleto grafoCompleto, JTextPane textPane, int cantidadDeGrupos) {

		this.grafo = grafoCompleto;
		this.textPane = textPane;
		this.cantidadDeGrupos = cantidadDeGrupos;
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegistrarUsuario = new JFrame();
		frmRegistrarUsuario.getContentPane().setBackground(new Color(135, 206, 250));
		frmRegistrarUsuario.setAutoRequestFocus(false);
		frmRegistrarUsuario.setResizable(false);
		frmRegistrarUsuario.setType(Type.POPUP);
		frmRegistrarUsuario.setBounds(100, 100, 220, 300);
		frmRegistrarUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRegistrarUsuario.getContentPane().setLayout(null);
		
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 36, 184, 30);
		frmRegistrarUsuario.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 11, 78, 14);
		frmRegistrarUsuario.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tango");
		lblNewLabel_1.setBounds(10, 92, 78, 14);
		frmRegistrarUsuario.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Folclore");
		lblNewLabel_2.setBackground(new Color(211, 211, 211));
		lblNewLabel_2.setBounds(105, 92, 78, 14);
		frmRegistrarUsuario.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_4 = new JLabel("Urbano");
		lblNewLabel_4.setBounds(105, 149, 78, 14);
		frmRegistrarUsuario.getContentPane().add(lblNewLabel_4);
		
		JSpinner spinnerTango = new JSpinner();
		spinnerTango.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerTango.setBounds(10, 117, 45, 25);
		frmRegistrarUsuario.getContentPane().add(spinnerTango);
		
		JSpinner spinnerFolclore = new JSpinner();
		spinnerFolclore.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerFolclore.setBounds(103, 117, 45, 25);
		frmRegistrarUsuario.getContentPane().add(spinnerFolclore);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setBounds(93, 36, 30, 20);
		frmRegistrarUsuario.getContentPane().add(spinner_2);
		
		JSpinner spinnerUrbano = new JSpinner();
		spinnerUrbano.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerUrbano.setBounds(104, 173, 45, 25);
		frmRegistrarUsuario.getContentPane().add(spinnerUrbano);
		
		JSpinner spinnerRock = new JSpinner();
		spinnerRock.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinnerRock.setBounds(10, 173, 45, 25);
		frmRegistrarUsuario.getContentPane().add(spinnerRock);
		
		JLabel lblNewLabel_4_2 = new JLabel("Rock Nacional");
		lblNewLabel_4_2.setBounds(10, 149, 83, 14);
		frmRegistrarUsuario.getContentPane().add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_3 = new JLabel("Intereses:");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 69, 78, 14);
		frmRegistrarUsuario.getContentPane().add(lblNewLabel_3);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBackground(new Color(222, 184, 135));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				Usuario user = new Usuario(txtNombre.getText(), (int)spinnerTango.getValue(), (int)spinnerFolclore.getValue(), (int)spinnerRock.getValue(), (int)spinnerUrbano.getValue());
				grafo.agregarUsuario(user);
				grafo.construirAristas();

				TextPaneManager.recargarPanelDeGrupos(grafo, cantidadDeGrupos, textPane);
				
			}
		});
		btnRegistrar.setBounds(10, 215, 184, 36);
		frmRegistrarUsuario.getContentPane().add(btnRegistrar);
		
	}
}
