package com.hackathon.agenda.modelo;

import java.util.Objects;

public class Contacto {
    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono.trim();
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre.trim(); }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido.trim(); }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono.trim(); }

    // Validación de teléfono con REGEX
    public static boolean validarTelefono(String telefono) {

        if (telefono == null) {
            return false;
        }

        String regex = "^(\\+34)?[6-9]\\d{8}$"
                + "|^(\\+34\\s+)?[6-9]\\d{2}(\\s+\\d{3}){2}$";

        return telefono.matches(regex);
    }

    // CRITERIO DE IGUALDAD: Nombre y Apellido (ignorando mayúsculas)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return Objects.equals(nombre.toLowerCase(), contacto.nombre.toLowerCase()) &&
                Objects.equals(apellido.toLowerCase(), contacto.apellido.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + telefono;
    }
}