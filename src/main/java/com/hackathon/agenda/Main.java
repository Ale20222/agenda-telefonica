package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;

import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Crear la agenda
        Agenda agenda = new Agenda();

        // Crear contactos
        Contacto[] contactosIniciales = {
        new Contacto("Martin", "Parra", "311444444"),
        new Contacto("Juan", "Perez", "312555555"),
        new Contacto("Maria", "Gomez", "313666666"),
        new Contacto("Carlos", "Rodriguez", "314777777"),
        new Contacto("Ana", "Martinez", "315888888"),
        new Contacto("Luis", "Fernandez", "316999999"),
        new Contacto("Sofia", "Lopez", "317123456"),
        new Contacto("Pedro", "Ramirez", "318234567"),
        new Contacto("Laura", "Torres", "319345678"),

    };

        // Mostrar el primer contacto (como en el ejemplo del profesor)

        for (Contacto contacto : contactosIniciales) {
            agenda.añadirContacto(contacto);
        }

        System.out.println("\n===== CONTACTOS DE LA AGENDA =====");
        agenda.listarContactos().forEach(System.out::println);

        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vista = new VentanaPrincipal();
            new AgendaControlador(agenda, vista);
            vista.setVisible(true);

        });
    }
}
