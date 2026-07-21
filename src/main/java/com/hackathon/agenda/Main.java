package com.hackathon.agenda;

import com.hackathon.agenda.modelo.Agenda;

import com.hackathon.agenda.modelo.Contacto;

import com.hackathon.agenda.vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {

        // Crear la agenda

        Agenda agenda = new Agenda();

        // Crear contactos

        Contacto primerContacto = new Contacto("Martin", "Parra", "311444444");

        Contacto segundoContacto = new Contacto("Juan", "Perez", "312555555");

        Contacto tercerContacto = new Contacto("Maria", "Gomez", "313666666");

        Contacto cuartoContacto = new Contacto("Carlos", "Rodriguez", "314777777");

        Contacto quintoContacto = new Contacto("Ana", "Martinez", "315888888");

        Contacto sextoContacto = new Contacto("Luis", "Fernandez", "316999999");

        Contacto septimoContacto = new Contacto("Sofia", "Lopez", "317123456");

        Contacto octavoContacto = new Contacto("Pedro", "Ramirez", "318234567");

        Contacto novenoContacto = new Contacto("Laura", "Torres", "319345678");

        Contacto decimoContacto = new Contacto("Valentina", "Morales", "320456789");

        // Agregar contactos a la agenda

        agenda.añadirContacto(primerContacto);

        agenda.añadirContacto(segundoContacto);

        agenda.añadirContacto(tercerContacto);

        agenda.añadirContacto(cuartoContacto);

        agenda.añadirContacto(quintoContacto);

        agenda.añadirContacto(sextoContacto);

        agenda.añadirContacto(septimoContacto);

        agenda.añadirContacto(octavoContacto);

        agenda.añadirContacto(novenoContacto);

        agenda.añadirContacto(decimoContacto);

        // Mostrar el primer contacto (como en el ejemplo del profesor)

        System.out.println(primerContacto);

        // Mostrar todos los contactos de la agenda

        System.out.println("\n===== CONTACTOS DE LA AGENDA =====");

        for (Contacto contacto : agenda.listarContactos()) {

            System.out.println(contacto);

        }

        // Abrir la ventana

        VentanaPrincipal vista = new VentanaPrincipal();

        vista.setVisible(true);

    }

}
