package com.hackathon.agenda.vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * Fabrica de componentes con el estilo visual comun de la aplicacion.
 *
 * <p>Esta clase solo configura la apariencia. No registra eventos ni contiene
 * reglas de negocio, por lo que conserva la separacion MVC.</p>
 */
public final class ComponentesGraficos {

    public static final Color FONDO = new Color(255, 255, 255);
    public static final Color SUPERFICIE = Color.WHITE;
    public static final Color PRIMARIO = new Color(37, 116, 235);
    public static final Color PRIMARIO_OSCURO = new Color(38, 94, 246);
    public static final Color PELIGRO = new Color(220, 38, 38);
    public static final Color EXITO = new Color(108, 254, 162);
    public static final Color TEXTO = new Color(0, 0, 0);
    public static final Color TEXTO_SECUNDARIO = new Color(145, 171, 209);
    public static final Color BORDE = new Color(1, 23, 251);

    private static final Font FUENTE_NORMAL = new Font("SansSerif", Font.PLAIN, 14);
    private static final Font FUENTE_ETIQUETA = new Font("SansSerif", Font.BOLD, 13);

    private ComponentesGraficos() {
        // Clase de utilidades: no debe instanciarse.
    }

    public static JLabel crearEtiqueta(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(FUENTE_ETIQUETA);
        etiqueta.setForeground(TEXTO);
        return etiqueta;
    }

    public static JTextField crearCampoTexto(String descripcionAccesible) {
        JTextField campo = new JTextField();
        campo.setFont(FUENTE_NORMAL);
        campo.setForeground(TEXTO);
        campo.setBackground(SUPERFICIE);
        campo.setCaretColor(PRIMARIO);
        campo.setPreferredSize(new Dimension(220, 38));
        campo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDE),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        campo.getAccessibleContext().setAccessibleName(descripcionAccesible);
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

    private static JButton crearBoton(
            String texto, Color fondo, Color primerPlano, Border borde) {
        JButton boton = new JButton(texto);
        boton.setFont(FUENTE_ETIQUETA);
        boton.setForeground(primerPlano);
        boton.setBackground(fondo);
        boton.setBorder(borde);
        boton.setFocusPainted(false);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setMargin(new Insets(9, 16, 9, 16));
        boton.setPreferredSize(new Dimension(120, 38));
        return boton;
    }

    public static JLabel crearEtiquetaEstado() {
        JLabel estado = new JLabel("Listo para gestionar contactos", SwingConstants.LEFT);
        estado.setFont(FUENTE_NORMAL);
        estado.setForeground(TEXTO_SECUNDARIO);
        estado.setBorder(BorderFactory.createEmptyBorder(10, 12, 10, 12));
        return estado;
    }
}
