package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import modelo.IGrafoCompleto;
import modelo.Usuario;
import vista.frmCargaDeDatos.PanelMode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Color;

public class frmRegistrarUsuario {
	private static frmRegistrarUsuario instancia = null;
    private JFrame frmRegistrarUsuario;
    private JTextField txtNombre;
    private IGrafoCompleto grafo;
    private JTextPane textPane;
    private int cantidadDeGrupos;
	private PanelMode panelMode;

    public static void crear(IGrafoCompleto grafoCompleto, JTextPane textPane, int cantidadDeGrupos, frmCargaDeDatos.PanelMode panelMode) {
        if (instancia != null) { 
            instancia.frmRegistrarUsuario.toFront();
            instancia.frmRegistrarUsuario.requestFocus();
            return;
        }
        EventQueue.invokeLater(() -> {
            try {
                instancia = new frmRegistrarUsuario(grafoCompleto, textPane, cantidadDeGrupos, panelMode);
                instancia.frmRegistrarUsuario.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private frmRegistrarUsuario(IGrafoCompleto grafoCompleto, JTextPane textPane, int cantidadDeGrupos, frmCargaDeDatos.PanelMode panelMode) {
        this.grafo = grafoCompleto;
        this.textPane = textPane;
        this.cantidadDeGrupos = cantidadDeGrupos;
        this.panelMode = panelMode;
        initialize();
    }


    private void initialize() {
        frmRegistrarUsuario = new JFrame();
        frmRegistrarUsuario.getContentPane().setBackground(new Color(135, 206, 250));
        frmRegistrarUsuario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frmRegistrarUsuario.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                instancia = null;
            }
        });
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

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblNombre.setBounds(10, 11, 78, 14);
        frmRegistrarUsuario.getContentPane().add(lblNombre);

        JLabel lblTango = new JLabel("Tango");
        lblTango.setBounds(10, 92, 78, 14);
        frmRegistrarUsuario.getContentPane().add(lblTango);

        JLabel lblFolclore = new JLabel("Folclore");
        lblFolclore.setBackground(new Color(211, 211, 211));
        lblFolclore.setBounds(105, 92, 78, 14);
        frmRegistrarUsuario.getContentPane().add(lblFolclore);

        JLabel lblRock = new JLabel("Rock Nacional");
        lblRock.setBounds(10, 149, 83, 14);
        frmRegistrarUsuario.getContentPane().add(lblRock);

        JLabel lblUrbano = new JLabel("Urbano");
        lblUrbano.setBounds(105, 149, 78, 14);
        frmRegistrarUsuario.getContentPane().add(lblUrbano);

        JLabel lblIntereses = new JLabel("Intereses:");
        lblIntereses.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblIntereses.setBounds(10, 69, 78, 14);
        frmRegistrarUsuario.getContentPane().add(lblIntereses);

        JSpinner spinnerTango = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        spinnerTango.setBounds(10, 117, 45, 25);
        frmRegistrarUsuario.getContentPane().add(spinnerTango);

        JSpinner spinnerFolclore = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        spinnerFolclore.setBounds(103, 117, 45, 25);
        frmRegistrarUsuario.getContentPane().add(spinnerFolclore);

        JSpinner spinnerRock = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        spinnerRock.setBounds(10, 173, 45, 25);
        frmRegistrarUsuario.getContentPane().add(spinnerRock);

        JSpinner spinnerUrbano = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        spinnerUrbano.setBounds(104, 173, 45, 25);
        frmRegistrarUsuario.getContentPane().add(spinnerUrbano);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(222, 184, 135));
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText().trim();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(frmRegistrarUsuario, "El nombre no puede estar vacío.");
                    return;
                }
                if (nombre.length() < 3) {
                    JOptionPane.showMessageDialog(frmRegistrarUsuario, "El nombre debe tener al menos 3 caracteres.");
                    return;
                }
                if (!nombre.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(frmRegistrarUsuario, "El nombre solo puede contener letras y espacios.");
                    return;
                }
                for (Usuario u : grafo.getUsuarios()) {
                    if (u.getNombre().equalsIgnoreCase(nombre)) {
                        JOptionPane.showMessageDialog(frmRegistrarUsuario, "Ya existe un usuario con ese nombre.");
                        return;
                    }
                }
                Usuario user = new Usuario(nombre, (int)spinnerTango.getValue(), (int)spinnerFolclore.getValue(), (int)spinnerRock.getValue(), (int)spinnerUrbano.getValue());
                grafo.agregarUsuario(user);
                grafo.construirAristas();
                if(panelMode == frmCargaDeDatos.PanelMode.USUARIOS) {
                    textPane.setText(grafo.imprimirUsuarios());
                } else {
                    TextPaneManager.recargarPanelDeGrupos(grafo, cantidadDeGrupos, textPane);
                }
                panelMode = frmCargaDeDatos.PanelMode.GRUPOS;
                TextPaneManager.recargarPanelDeGrupos(grafo, cantidadDeGrupos, textPane);
                
            }
        });
        btnRegistrar.setBounds(10, 215, 184, 36);
        frmRegistrarUsuario.getContentPane().add(btnRegistrar);
    }
}
