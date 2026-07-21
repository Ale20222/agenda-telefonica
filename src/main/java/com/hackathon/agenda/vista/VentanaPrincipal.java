package com.hackathon.agenda.vista;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * Ventana principal de la agenda telefonica.
 *
 * <p>La clase construye y expone los componentes Swing. El controlador es el
 * responsable de registrar listeners, validar datos y coordinar el modelo.</p>
 */
public class VentanaPrincipal extends JFrame {

    private static final String[] COLUMNAS = {"Nombre", "Apellido", "Telefono"};

    private final JTextField campoNombre;
    private final JTextField campoApellido;
    private final JTextField campoTelefono;
    private final JButton botonAgregar;
    private final JButton botonModificar;
    private final JButton botonEliminar;
    private final JButton botonLimpiar;
    private final DefaultTableModel modeloTabla;
    private final JTable tablaContactos;
    private final JLabel etiquetaEstado;
    private final JLabel etiquetaCapacidad;

    public VentanaPrincipal() {
        super("Agenda telefonica");

        campoNombre = ComponentesGraficos.crearCampoTexto("Nombre del contacto");
        campoApellido = ComponentesGraficos.crearCampoTexto("Apellido del contacto");
        campoTelefono = ComponentesGraficos.crearCampoTexto("Telefono del contacto");

        botonAgregar = ComponentesGraficos.crearBotonPrimario("Agregar");
        botonModificar = ComponentesGraficos.crearBotonSecundario("Modificar");
        botonEliminar = ComponentesGraficos.crearBotonPeligro("Eliminar");
        botonLimpiar = ComponentesGraficos.crearBotonSecundario("Limpiar");

        modeloTabla = new DefaultTableModel(COLUMNAS, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaContactos = new JTable(modeloTabla);
        etiquetaEstado = ComponentesGraficos.crearEtiquetaEstado();
        etiquetaCapacidad = new JLabel("0 contactos", SwingConstants.RIGHT);

        configurarVentana();
        construirContenido();
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(880, 580));
        setSize(980, 650);
        setLocationRelativeTo(null);
        getContentPane().setBackground(ComponentesGraficos.FONDO);
    }

    private void construirContenido() {
        JPanel contenido = new JPanel(new BorderLayout(18, 18));
        contenido.setBackground(ComponentesGraficos.FONDO);
        contenido.setBorder(BorderFactory.createEmptyBorder(22, 24, 20, 24));

        contenido.add(crearEncabezado(), BorderLayout.NORTH);
        contenido.add(crearPanelCentral(), BorderLayout.CENTER);
        contenido.add(crearPanelEstado(), BorderLayout.SOUTH);
        setContentPane(contenido);
    }

    private JPanel crearEncabezado() {
        JPanel encabezado = new JPanel();
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.setOpaque(false);

        JLabel titulo = new JLabel("Agenda telefonica");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 27));
        titulo.setForeground(ComponentesGraficos.TEXTO);

        JLabel subtitulo = new JLabel("Administra tus contactos desde un solo lugar");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(ComponentesGraficos.TEXTO_SECUNDARIO);

        encabezado.add(titulo);
        encabezado.add(Box.createVerticalStrut(4));
        encabezado.add(subtitulo);
        return encabezado;
    }

    private JPanel crearPanelCentral() {
        JPanel central = new JPanel(new BorderLayout(18, 0));
        central.setOpaque(false);
        central.add(crearPanelFormulario(), BorderLayout.WEST);
        central.add(crearPanelTabla(), BorderLayout.CENTER);
        return central;
    }

    private JPanel crearPanelFormulario() {
        JPanel formulario = crearTarjeta();
        formulario.setPreferredSize(new Dimension(300, 430));
        formulario.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 7, 0);

        JLabel titulo = new JLabel("Datos del contacto");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(ComponentesGraficos.TEXTO);
        agregarFila(formulario, titulo, gbc, 0, 0, 14);

        agregarFila(formulario, ComponentesGraficos.crearEtiqueta("Nombre"), gbc, 1, 0, 6);
        agregarFila(formulario, campoNombre, gbc, 2, 0, 14);
        agregarFila(formulario, ComponentesGraficos.crearEtiqueta("Apellido"), gbc, 3, 0, 6);
        agregarFila(formulario, campoApellido, gbc, 4, 0, 14);
        agregarFila(formulario, ComponentesGraficos.crearEtiqueta("Telefono"), gbc, 5, 0, 6);
        agregarFila(formulario, campoTelefono, gbc, 6, 0, 18);

        JLabel ayuda = new JLabel("Ej.: +34 600 000 000 o 600000000");
        ayuda.setFont(new Font("SansSerif", Font.PLAIN, 11));
        ayuda.setForeground(ComponentesGraficos.TEXTO_SECUNDARIO);
        agregarFila(formulario, ayuda, gbc, 7, 0, 16);

        JPanel accionesPrincipales = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        accionesPrincipales.setOpaque(false);
        accionesPrincipales.add(botonAgregar);
        accionesPrincipales.add(botonModificar);
        agregarFila(formulario, accionesPrincipales, gbc, 8, 0, 9);

        JPanel accionesSecundarias = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        accionesSecundarias.setOpaque(false);
        accionesSecundarias.add(botonEliminar);
        accionesSecundarias.add(botonLimpiar);
        agregarFila(formulario, accionesSecundarias, gbc, 9, 1, 0);

        return formulario;
    }

    private void agregarFila(
            JPanel panel, java.awt.Component componente, GridBagConstraints gbc,
            int fila, double pesoVertical, int margenInferior) {
        gbc.gridy = fila;
        gbc.weighty = pesoVertical;
        gbc.anchor = pesoVertical > 0
                ? GridBagConstraints.SOUTHWEST
                : GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(0, 0, margenInferior, 0);
        panel.add(componente, gbc);
    }

    private JPanel crearPanelTabla() {
        JPanel panel = crearTarjeta();
        panel.setLayout(new BorderLayout(0, 12));

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setOpaque(false);

        JLabel titulo = new JLabel("Contactos guardados");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setForeground(ComponentesGraficos.TEXTO);

        etiquetaCapacidad.setFont(new Font("SansSerif", Font.PLAIN, 13));
        etiquetaCapacidad.setForeground(ComponentesGraficos.TEXTO_SECUNDARIO);
        cabecera.add(titulo, BorderLayout.WEST);
        cabecera.add(etiquetaCapacidad, BorderLayout.EAST);

        configurarTabla();
        JScrollPane desplazamiento = new JScrollPane(tablaContactos);
        desplazamiento.setBorder(BorderFactory.createLineBorder(ComponentesGraficos.BORDE));
        desplazamiento.getViewport().setBackground(ComponentesGraficos.SUPERFICIE);

        panel.add(cabecera, BorderLayout.NORTH);
        panel.add(desplazamiento, BorderLayout.CENTER);
        return panel;
    }

    private void configurarTabla() {
        tablaContactos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaContactos.setForeground(ComponentesGraficos.TEXTO);
        tablaContactos.setBackground(ComponentesGraficos.SUPERFICIE);
        tablaContactos.setGridColor(new java.awt.Color(226, 232, 240));
        tablaContactos.setRowHeight(34);
        tablaContactos.setShowVerticalLines(false);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaContactos.setSelectionBackground(new java.awt.Color(219, 234, 254));
        tablaContactos.setSelectionForeground(ComponentesGraficos.TEXTO);
        tablaContactos.setFillsViewportHeight(true);

        JTableHeader cabecera = tablaContactos.getTableHeader();
        cabecera.setFont(new Font("SansSerif", Font.BOLD, 13));
        cabecera.setForeground(ComponentesGraficos.TEXTO);
        cabecera.setBackground(new java.awt.Color(241, 245, 249));
        cabecera.setReorderingAllowed(false);
        cabecera.setPreferredSize(new Dimension(cabecera.getPreferredSize().width, 36));
    }

    private JPanel crearPanelEstado() {
        JPanel estado = new JPanel(new BorderLayout());
        estado.setBackground(new java.awt.Color(241, 245, 249));
        estado.setBorder(BorderFactory.createLineBorder(ComponentesGraficos.BORDE));
        estado.add(etiquetaEstado, BorderLayout.CENTER);
        return estado;
    }

    private JPanel crearTarjeta() {
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(ComponentesGraficos.SUPERFICIE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new java.awt.Color(226, 232, 240)),
                BorderFactory.createEmptyBorder(18, 18, 18, 18)
        ));
        return tarjeta;
    }

    // Componentes expuestos para que el controlador registre los listeners.

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonModificar() {
        return botonModificar;
    }

    public JButton getBotonEliminar() {
        return botonEliminar;
    }

    public JButton getBotonLimpiar() {
        return botonLimpiar;
    }

    public JTable getTablaContactos() {
        return tablaContactos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public String getNombre() {
        return campoNombre.getText().trim();
    }

    public String getApellido() {
        return campoApellido.getText().trim();
    }

    public String getTelefono() {
        return campoTelefono.getText().trim();
    }

    public int getFilaSeleccionada() {
        return tablaContactos.getSelectedRow();
    }

    public void cargarFormulario(String nombre, String apellido, String telefono) {
        campoNombre.setText(nombre);
        campoApellido.setText(apellido);
        campoTelefono.setText(telefono);
    }

    public void limpiarFormulario() {
        campoNombre.setText("");
        campoApellido.setText("");
        campoTelefono.setText("");
        tablaContactos.clearSelection();
        campoNombre.requestFocusInWindow();
    }

    /**
     * Sustituye el contenido de la tabla sin acoplar la vista a Contacto.
     * Cada arreglo debe contener nombre, apellido y telefono, en ese orden.
     */
    public void actualizarTabla(List<String[]> filas) {
        modeloTabla.setRowCount(0);
        for (String[] fila : filas) {
            if (fila != null && fila.length >= COLUMNAS.length) {
                modeloTabla.addRow(new Object[]{fila[0], fila[1], fila[2]});
            }
        }
    }

    public void mostrarError(String mensaje) {
        etiquetaEstado.setText(mensaje);
        etiquetaEstado.setForeground(ComponentesGraficos.PELIGRO);
    }

    public void mostrarExito(String mensaje) {
        etiquetaEstado.setText(mensaje);
        etiquetaEstado.setForeground(ComponentesGraficos.EXITO);
    }

    public void mostrarInformacion(String mensaje) {
        etiquetaEstado.setText(mensaje);
        etiquetaEstado.setForeground(ComponentesGraficos.TEXTO_SECUNDARIO);
    }

    public void actualizarCapacidad(int contactosRegistrados, int capacidadMaxima) {
        etiquetaCapacidad.setText(
                contactosRegistrados + " de " + capacidadMaxima + " contactos");
    }
}
