package com.hackathon.agenda.modelo;

import java.util.Objects;

public class Contacto {

    private String nombre;
    private String apellido;
    private String telefono;

    // ========== CONSTRUCTORES ==========

    /**
     * Constructor COMPLETO - Para AGREGAR y MODIFICAR contactos
     * VALIDA que el teléfono sea válido
     */
    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre != null ? nombre.trim() : "";
        this.apellido = apellido != null ? apellido.trim() : "";

        // ✅ Validar que el teléfono no esté vacío
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ El teléfono no puede estar vacío");
        }

        String telefonoLimpio = telefono.trim().replaceAll("\\s+", "");
        if (!validarTelefono(telefonoLimpio)) {
            throw new IllegalArgumentException("❌ El teléfono '" + telefono + "' no tiene un formato válido");
        }
        this.telefono = telefonoLimpio;
    }

    /**
     * Constructor para BÚSQUEDA - NO valida el teléfono
     * Solo usa nombre y apellido para buscar
     */
    public Contacto(String nombre, String apellido) {
        this.nombre = nombre != null ? nombre.trim() : "";
        this.apellido = apellido != null ? apellido.trim() : "";
        this.telefono = ""; // Teléfono vacío para búsqueda
    }

    // ========== GETTERS Y SETTERS ==========

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ El nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ El apellido no puede estar vacío");
        }
        this.apellido = apellido.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ El teléfono no puede estar vacío");
        }

        String telefonoLimpio = telefono.trim().replaceAll("\\s+", "");
        if (!validarTelefono(telefonoLimpio)) {
            throw new IllegalArgumentException("❌ El teléfono '" + telefono + "' no tiene un formato válido");
        }

        this.telefono = telefonoLimpio;
    }

    // ========== VALIDACIÓN DE TELÉFONO ==========

    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }

        String telefonoLimpio = telefono.trim().replaceAll("\\s+", "");
        String soloNumeros = telefonoLimpio.replaceAll("[+\\-.]", "");

        if (soloNumeros.matches("\\d+")) {
            int longitud = soloNumeros.length();
            return longitud >= 7 && longitud <= 15;
        }

        return false;
    }

    // ========== CRITERIO DE IGUALDAD ==========

    /**
     * ✅ CRITERIO DE IGUALDAD: Nombre y Apellido (ignorando mayúsculas)
     * Según el documento: "Dos contactos se consideran iguales si tienen el mismo nombre y apellido"
     */
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
        return String.format("%-12s %-12s %s", nombre, apellido, telefono);
    }
}
