package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.persistencia.Persistencia;
import com.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        int capacidad = mostrarDialogoCapacidad();

        System.out.println("📋 Iniciando agenda con capacidad: " + capacidad);

        // Crear la agenda con la capacidad configurada
        Agenda agenda = new Agenda(capacidad);

        // ✅ Comentado: Ya no se agregan contactos de prueba automáticamente
        // agregarContactosDePrueba(agenda);

        System.out.println("\n===== CONTACTOS DE LA AGENDA =====");
        if (agenda.getCantidadContactos() == 0) {
            System.out.println("(La agenda está vacía)");
        } else {
            agenda.listarContactos().forEach(System.out::println);
        }
        System.out.println("📊 Espacios libres: " + agenda.espaciosLibres() + "/" + capacidad);
        System.out.println("===================================\n");

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vista = new VentanaPrincipal();
            new AgendaControlador(agenda, vista);
            vista.setVisible(true);
            System.out.println("🚀 Aplicación iniciada correctamente");
            System.out.println("💾 Ruta del archivo: " + Persistencia.getRutaArchivo());
        });
    }

    // ========== MÉTODO PARA MOSTRAR DIÁLOGO DE CAPACIDAD ==========
    private static int mostrarDialogoCapacidad() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Ingresa la capacidad máxima de la agenda:");
        label.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JTextField campoCapacidad = new JTextField("10", 10);
        campoCapacidad.setAlignmentX(JTextField.CENTER_ALIGNMENT);
        campoCapacidad.setHorizontalAlignment(JTextField.CENTER);

        JLabel ejemplo = new JLabel("(Ejemplo: 10, 15, 20, 50, 100...)");
        ejemplo.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        ejemplo.setFont(new java.awt.Font("Arial", java.awt.Font.ITALIC, 10));
        ejemplo.setForeground(java.awt.Color.GRAY);

        panel.add(Box.createVerticalStrut(10));
        panel.add(label);
        panel.add(Box.createVerticalStrut(10));
        panel.add(campoCapacidad);
        panel.add(Box.createVerticalStrut(5));
        panel.add(ejemplo);
        panel.add(Box.createVerticalStrut(10));

        int resultado = JOptionPane.showConfirmDialog(
                null,
                panel,
                "⚙️ Configuración de Agenda",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (resultado != JOptionPane.OK_OPTION) {
            System.out.println("⚠️ Usando capacidad por defecto: 10");
            return 10;
        }

        String input = campoCapacidad.getText().trim();
        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No ingresaste un valor. Usando 10 por defecto.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return 10;
        }

        try {
            int capacidad = Integer.parseInt(input);
            if (capacidad < 1) {
                JOptionPane.showMessageDialog(null,
                        "La capacidad debe ser mayor a 0. Usando 10 por defecto.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return 10;
            }
            return capacidad;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "'" + input + "' no es un número válido. Usando 10 por defecto.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return 10;
        }
    }
}