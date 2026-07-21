package com.hackathon.agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;

public class Main {
    public static void main(String[] args) {
        Contacto primerContacto = new Contacto("Martin", "Parra", "311444444");
        System.out.println(primerContacto);
        VentanaPrincipal vista = new VentanaPrincipal();
        vista.setVisible(true);


    }
}