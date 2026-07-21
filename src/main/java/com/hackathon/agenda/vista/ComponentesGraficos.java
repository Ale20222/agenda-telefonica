
package com.hackathon.agenda.vista;

import java.awt.*;
        import javax.swing.*;
        import javax.swing.border.Border;

/** Configura la apariencia sin registrar eventos ni aplicar reglas de negocio. */
public final class ComponentesGraficos {

    public static final Color FONDO = Color.WHITE;
    public static final Color SUPERFICIE = Color.WHITE;
    public static final Color PRIMARIO = new Color(37, 116, 235);
    public static final Color PELIGRO = new Color(220, 38, 38);
    public static final Color EXITO = new Color(20, 150, 70);
    public static final Color TEXTO = Color.BLACK;
    public static final Color TEXTO_SECUNDARIO = new Color(100, 120, 150);
    public static final Color BORDE = new Color(1, 23, 251);

    private static final Font FUENTE = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font FUENTE_ETIQUETA = new Font("SansSerif", Font.BOLD, 13);

    private ComponentesGraficos() { }

    public static JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(FUENTE_ETIQUETA);
        etiqueta.setForeground(TEXTO);
        return etiqueta;
    }

    public static JTextField crearCampoTexto(String nombreAccesible) {
        JTextField campo = new JTextField();
        campo.setFont(FUENTE);
        campo.setPreferredSize(new Dimension(220, 38));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        campo.getAccessibleContext().setAccessibleName(nombreAccesible);
        return campo;
    }

    public static JButton crearBotonPrimario(String texto) {
        return crearBoton(texto, PRIMARIO, Color.WHITE,
                BorderFactory.createLineBorder(PRIMARIO));
    }

    public static JButton crearBotonPeligro(String texto) {
        return crearBoton(texto, SUPERFICIE, PELIGRO,
                BorderFactory.createLineBorder(PELIGRO));
    }

    public static JButton crearBotonSecundario(String texto) {
        return crearBoton(texto, SUPERFICIE, TEXTO,
                BorderFactory.createLineBorder(BORDE));
    }

    private static JButton crearBoton(String texto, Color fondo,
                                      Color letra, Border borde) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_ETIQUETA);
        boton.setForeground(letra);
        boton.setBackground(fondo);
        boton.setBorder(borde);
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setMargin(new Insets(9, 16, 9, 16));
        boton.setPreferredSize(new Dimension(120, 38));
        return boton;
    }

    public static JLabel crearEtiquetaEstado() {
        JLabel estado = new JLabel("Listo para gestionar contactos");
        estado.setFont(FUENTE);
        estado.setForeground(TEXTO_SECUNDARIO);
        estado.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        return estado;
    }
}
