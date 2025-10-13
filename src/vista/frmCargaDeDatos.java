package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
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
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;

public class frmCargaDeDatos {

	private JFrame frmBuscadorDeSimilaridades;
	private JButton btnMostrarMST;
	private JSpinner spinnerCantidadDeGrupos;
	private JLabel lblNewLabel;
	private JTextPane textPane;
	private JButton btnMostrarGrupos;

	@SuppressWarnings("unused")
	private IAgrupadorUsuarios agrupador;
	private IKruskalMST kruskal;
	private IGrafoCompleto grafo;
	public enum PanelMode { USUARIOS, GRUPOS }
	private PanelMode panelMode = PanelMode.USUARIOS;

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
		frmBuscadorDeSimilaridades.setBounds(100, 100, 498, 420);
		frmBuscadorDeSimilaridades.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBuscadorDeSimilaridades.getContentPane().setLayout(null);
		StyleJOptionPanel.StyleJOptionPannel();
		
		this.grafo = new GrafoCompleto();
		
		this.kruskal = new KruskalMST((GrafoCompleto)grafo);
		
		this.agrupador = new AgrupadorUsuarios(kruskal.getMST());
		
		
		btnMostrarMST = new JButton("Mostrar usuarios");
		btnMostrarMST.setBackground(new Color(244, 164, 96));
		btnMostrarMST.setBounds(10, 68, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnMostrarMST);
		
		

		JButton btnEliminarUsuario = new JButton("Eliminar usuario");
		btnEliminarUsuario.setBackground(new Color(255, 102, 102));
		btnEliminarUsuario.setVisible(false); 
		btnMostrarMST.addActionListener(e -> {
		    textPane.setText(grafo.imprimirUsuarios());
		    panelMode = PanelMode.USUARIOS;
		    btnEliminarUsuario.setVisible(!grafo.getUsuarios().isEmpty());
		});


		
		btnEliminarUsuario.setBounds(10, 279, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnEliminarUsuario);

		spinnerCantidadDeGrupos = new JSpinner();
		spinnerCantidadDeGrupos.setBackground(new Color(211, 211, 211));
		spinnerCantidadDeGrupos.setModel(new SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(2), null, Integer.valueOf(1)));
		spinnerCantidadDeGrupos.setBounds(10, 179, 50, 30);
		frmBuscadorDeSimilaridades.getContentPane().add(spinnerCantidadDeGrupos);
		spinnerCantidadDeGrupos.addChangeListener(e -> {
		    int cantidadGrupos = (int) spinnerCantidadDeGrupos.getValue();
		    int cantidadUsuarios = grafo.getUsuarios().size();
		    if (cantidadGrupos > cantidadUsuarios) {
		        JOptionPane.showMessageDialog(frmBuscadorDeSimilaridades,
		            "No puede haber más grupos que usuarios (" + cantidadUsuarios + ").");
		        spinnerCantidadDeGrupos.setValue(cantidadUsuarios);
		    }
		});

		btnEliminarUsuario.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String nombre = JOptionPane.showInputDialog(frmBuscadorDeSimilaridades, "Ingrese el nombre del usuario a eliminar:");
		        if (nombre == null || nombre.trim().isEmpty()) {
		            return;
		        }

		        Usuario usuarioAEliminar = null;
		        for (Usuario u : grafo.getUsuarios()) {
		            if (u.getNombre().equalsIgnoreCase(nombre.trim())) {
		                usuarioAEliminar = u;
		                break;
		            }
		        }

		        if (usuarioAEliminar == null) {
		            JOptionPane.showMessageDialog(frmBuscadorDeSimilaridades, "No se encontró un usuario con ese nombre.");
		            return;
		        }

		        grafo.eliminarUsuario(usuarioAEliminar);
		        grafo.construirAristas();
		        if(panelMode == PanelMode.USUARIOS) {
		            textPane.setText(grafo.imprimirUsuarios());
		        } else {
		            TextPaneManager.recargarPanelDeGrupos(grafo, (int)spinnerCantidadDeGrupos.getValue(), textPane);
		        }

		        JOptionPane.showMessageDialog(frmBuscadorDeSimilaridades, "Usuario eliminado correctamente.");
		    }
		});

		
		
		
		
		lblNewLabel = new JLabel("Cantidad de grupos:");
		lblNewLabel.setBounds(10, 154, 134, 14);
		frmBuscadorDeSimilaridades.getContentPane().add(lblNewLabel);
		
		textPane = new JTextPane();
		textPane.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
		textPane.setBackground(new Color(248, 248, 255));
		textPane.setEditable(false);
		textPane.setBounds(169, 11, 285, 359);
		
		
		JScrollPane scrollPane = new JScrollPane(textPane);
		scrollPane.setBounds(169, 11, 300, 359);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frmBuscadorDeSimilaridades.getContentPane().add(scrollPane);
		
		btnMostrarGrupos = new JButton("Mostrar grupos");
		btnMostrarGrupos.setBackground(new Color(244, 164, 96));
		btnMostrarGrupos.addActionListener(e -> {
		    TextPaneManager.recargarPanelDeGrupos(grafo, (int)spinnerCantidadDeGrupos.getValue(), textPane);
		    panelMode = PanelMode.GRUPOS;
		    btnEliminarUsuario.setVisible(false);
		});

		btnMostrarGrupos.setBounds(10, 111, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnMostrarGrupos);
		

		JButton btnRegistrar = new JButton("Registrar usuario");
		btnRegistrar.setBackground(new Color(127, 255, 0));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmRegistrarUsuario.crear(grafo, textPane, (int)spinnerCantidadDeGrupos.getValue(), panelMode);

			}
		});
		btnRegistrar.setBounds(10, 25, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnRegistrar);
		JButton btnEliminarGrupos = new JButton("Eliminar Grupos");
		btnEliminarGrupos.setBackground(new Color(255, 204, 153));
		btnEliminarGrupos.setBounds(10, 322, 149, 32);
		frmBuscadorDeSimilaridades.getContentPane().add(btnEliminarGrupos);


		btnEliminarGrupos.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        List<Usuario> usuarios = List.copyOf(grafo.getUsuarios()); 
		        for (Usuario u : usuarios) {
		            grafo.eliminarUsuario(u);
		        }

		        TextPaneManager.limpiarTextPane(textPane);

		        panelMode = PanelMode.GRUPOS;
		        spinnerCantidadDeGrupos.setValue(2);
		        JOptionPane.showMessageDialog(frmBuscadorDeSimilaridades, 
		            "Se eliminaron los grupos y todos los usuarios. Podés generar nuevos.");
		    }
		});



	}
}