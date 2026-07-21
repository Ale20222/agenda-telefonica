package com.hackathon.agenda.vista;

import com.hackathon.agenda.modelo.Contacto;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;

    private JButton btnAgregar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnLimpiar;

    private JPanel panelFormulario;
    private JPanel panelBotones;
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;
    private JLabel lblEstado;
    private JLabel lblEspacios;

    public VentanaPrincipal() {
        configurarVentana();
        inicializarComponentes();
        organizarLayout();
    }

    private void configurarVentana() {
        setTitle("Agenda de Contactos");
        setSize(750, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(ComponentesGraficos.FONDO);
    }

    private void inicializarComponentes() {
        txtNombre = ComponentesGraficos.crearCampoTexto("Nombre");
        txtApellido = ComponentesGraficos.crearCampoTexto("Apellido");
        txtTelefono = ComponentesGraficos.crearCampoTexto("Telefono");

        btnAgregar = ComponentesGraficos.crearBotonPrimario("Agregar");
        btnModificar = ComponentesGraficos.crearBotonSecundario("Modificar");
        btnEliminar = ComponentesGraficos.crearBotonPeligro("Eliminar");
        btnBuscar = ComponentesGraficos.crearBotonSecundario("Buscar");
        btnLimpiar = ComponentesGraficos.crearBotonSecundario("Limpiar");

        modeloTabla = new DefaultTableModel(
                new String[]{"Nombre", "Apellido", "Telefono"}, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaContactos.getSelectionModel()
                .addListSelectionListener(e -> cargarFilaSeleccionada());

        lblEstado = ComponentesGraficos.crearEtiquetaEstado();
        lblEspacios = new JLabel("Espacios libres: 0");
    }

    private void organizarLayout() {
        panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos del Contacto"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        agregarCampo("Nombre:", txtNombre, 0, gbc);
        agregarCampo("Apellido:", txtApellido, 1, gbc);
        agregarCampo("Telefono:", txtTelefono, 2, gbc);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelBotones.add(btnAgregar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnLimpiar);

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.add(panelFormulario, BorderLayout.CENTER);
        panelSuperior.add(panelBotones, BorderLayout.SOUTH);

        JPanel panelEstado = new JPanel(new BorderLayout());
        panelEstado.add(lblEstado, BorderLayout.CENTER);
        panelEstado.add(lblEspacios, BorderLayout.EAST);

        add(panelSuperior, BorderLayout.NORTH);
        add(new JScrollPane(tablaContactos), BorderLayout.CENTER);
        add(panelEstado, BorderLayout.SOUTH);
    }

    private void agregarCampo(String texto, JTextField campo, int fila,
                              GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = fila;
        panelFormulario.add(ComponentesGraficos.crearEtiqueta(texto), gbc);
        gbc.gridx = 1;
        panelFormulario.add(campo, gbc);
    }

    private void cargarFilaSeleccionada() {
        int fila = tablaContactos.getSelectedRow();
        if (fila >= 0) {
            cargarDatosEnFormulario(
                    modeloTabla.getValueAt(fila, 0).toString(),
                    modeloTabla.getValueAt(fila, 1).toString(),
                    modeloTabla.getValueAt(fila, 2).toString());
        }
    }

    public void mostrarContactos(List<Contacto> contactos) {
        modeloTabla.setRowCount(0);
        for (Contacto contacto : contactos) {
            modeloTabla.addRow(new Object[]{contacto.getNombre(),
                    contacto.getApellido(), contacto.getTelefono()});
        }
    }

    public void limpiarCampos() {
        cargarDatosEnFormulario("", "", "");
        tablaContactos.clearSelection();
        txtNombre.requestFocusInWindow();
    }

    public void cargarDatosEnFormulario(String nombre, String apellido, String telefono) {
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtTelefono.setText(telefono);
    }

    public void mostrarMensaje(String mensaje, boolean esError) {
        lblEstado.setText(mensaje);
        lblEstado.setForeground(esError
                ? ComponentesGraficos.PELIGRO : ComponentesGraficos.EXITO);
    }

    public void mostrarEspaciosLibres(int espacios) {
        lblEspacios.setText("Espacios libres: " + espacios);
    }

    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtApellido() { return txtApellido; }
    public JTextField getTxtTelefono() { return txtTelefono; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnModificar() { return btnModificar; }
    public JButton getBtnEliminar() { return btnEliminar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JTable getTablaContactos() { return tablaContactos; }
    public JPanel getPanelFormulario() { return panelFormulario; }
    public JPanel getPanelBotones() { return panelBotones; }
}