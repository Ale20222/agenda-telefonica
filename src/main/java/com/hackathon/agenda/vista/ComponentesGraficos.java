package com.hackathon.agenda.vista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/** Configura la apariencia sin registrar eventos ni aplicar reglas de negocio. */
public final class ComponentesGraficos {

    public static final Color FONDO = new Color(244, 247, 251);
    public static final Color SUPERFICIE = Color.WHITE;
    public static final Color PRIMARIO = new Color(37, 99, 235);
    public static final Color PELIGRO = new Color(220, 38, 38);
    public static final Color EXITO = new Color(22, 163, 74);
    public static final Color TEXTO = new Color(15, 23, 42);
    public static final Color TEXTO_SECUNDARIO = new Color(100, 116, 139);
    public static final Color BORDE = new Color(203, 213, 225);

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
        campo.setForeground(TEXTO);
        campo.setBackground(SUPERFICIE);
        campo.setCaretColor(PRIMARIO);
        campo.setPreferredSize(new Dimension(240, 42));
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
        return crearBoton(texto, new Color(254, 242, 242), PELIGRO,
                BorderFactory.createEmptyBorder(10, 14, 10, 14));
    }

    public static JButton crearBotonSecundario(String texto) {
        return crearBoton(texto, new Color(239, 246, 255), PRIMARIO,
                BorderFactory.createEmptyBorder(10, 14, 10, 14));
    }

    private static JButton crearBoton(String texto, Color fondo,
                                      Color letra, Border borde) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_ETIQUETA);
        boton.setForeground(letra);
        boton.setBackground(fondo);
        boton.setBorder(borde);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setMargin(new Insets(9, 16, 9, 16));
        boton.setPreferredSize(new Dimension(125, 42));
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
