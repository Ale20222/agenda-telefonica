package com.hackathon.agenda.vista;

import javax.swing.*;
import java.awt.*;


public class VentanaPrincipal extends JFrame {

    // Campos de texto del formulario
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;

    // Botones de acción
    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    // Paneles principales (públicos como atributos protegidos por si
    // Persona 2 necesita referenciarlos para agregar la tabla o el panel de estado)
    private JPanel panelFormulario;
    private JPanel panelBotones;

    public VentanaPrincipal() {
        configurarVentana();
        inicializarComponentes();
        organizarLayout();
    }


    private void configurarVentana() {
        setTitle("Agenda de Contactos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centra la ventana en pantalla
        setLayout(new BorderLayout(10, 10));
    }


    private void inicializarComponentes() {
        txtNombre = new JTextField(15);
        txtApellido = new JTextField(15);
        txtTelefono = new JTextField(15);

        btnAgregar = new JButton("Agregar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnBuscar = new JButton("Buscar");
        btnLimpiar = new JButton("Limpiar");
    }


    private void organizarLayout() {
        // Panel de formulario con GridBagLayout
        panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Contacto"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 0: Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        panelFormulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtNombre, gbc);

        // Fila 1: Apellido
        gbc.gridx = 0; gbc.gridy = 1;
        panelFormulario.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtApellido, gbc);

        // Fila 2: Teléfono
        gbc.gridx = 0; gbc.gridy = 2;
        panelFormulario.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        panelFormulario.add(txtTelefono, gbc);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtNombre.requestFocus();
    }


    public void cargarDatosEnFormulario(String nombre, String apellido, String telefono) {
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtTelefono.setText(telefono);
    }


    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellido() { return txtApellido; }
    public JTextField getTxtTelefono() { return txtTelefono; }


    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }


    public JPanel getPanelFormulario() { return panelFormulario; }
    public JPanel getPanelBotones() { return panelBotones; }
}
