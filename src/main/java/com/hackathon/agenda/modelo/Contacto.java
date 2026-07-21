package com.hackathon.agenda.modelo;

import java.util.Objects;

public class Contacto {

    private String nombre;
    private String apellido;
    private String telefono;

    public Contacto(String nombre, String apellido, String telefono) {
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        setTelefono(telefono); // Usa el setter con validación
    }

    // Getters y Setters
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

        // 🔥 VALIDACIÓN MÁS FLEXIBLE
        if (!validarTelefono(telefonoLimpio)) {
            throw new IllegalArgumentException("❌ El teléfono '" + telefono + "' no tiene un formato válido. " +
                    "Formatos aceptados: 311444444, +57311444444, 311 444 444, 311-444-444");
        }

        this.telefono = telefonoLimpio;
    }

    /**
     * 🔥 VALIDACIÓN DE TELÉFONO MEJORADA (Acepta múltiples formatos)
     */
    public static boolean validarTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            return false;
        }

        String telefonoLimpio = telefono.trim().replaceAll("\\s+", "");

        // 🔥 FORMATOS ACEPTADOS:
        // - 311444444 (7-15 dígitos, solo números)
        // - +57311444444 (con prefijo internacional)
        // - 311-444-444 (con guiones)
        // - 311 444 444 (con espacios)
        // - 311.444.444 (con puntos)

        String regex = "^(\\+\\d{1,3})?[0-9]{7,15}$";

        // Limpiar caracteres especiales para la validación
        String soloNumeros = telefonoLimpio.replaceAll("[+\\-.]", "");

        // Si después de limpiar solo hay números, validar longitud
        if (soloNumeros.matches("\\d+")) {
            int longitud = soloNumeros.length();
            // Aceptar entre 7 y 15 dígitos (estándar internacional)
            return longitud >= 7 && longitud <= 15;
        }

        return false;
    }

    /**
     * 🔥 CRITERIO DE IGUALDAD: Por teléfono (más fiable)
     * Dos contactos son iguales si tienen el mismo teléfono
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacto contacto = (Contacto) o;
        return Objects.equals(telefono, contacto.telefono);
    }

    @Override
    public int hashCode() {
        return Objects.hash(telefono);
    }

    @Override
    public String toString() {
        return String.format("%-12s %-12s %s", nombre, apellido, telefono);
    }
}