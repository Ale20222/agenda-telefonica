package com.hackathon.agenda.modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Agenda {

    private static final int TAMANO_POR_DEFECTO = 10;

    private final List<Contacto> contactos;
    private final int capacidadMaxima;

    // capacidad de la agenda (10)

    public Agenda() {
        this(TAMANO_POR_DEFECTO);
    }

    // configuración
    public Agenda(int capacidadMaxima) {
        if (capacidadMaxima <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    // Validación de especificaciones para que se añada un contacto válido
    public boolean añadirContacto(Contacto c) {
        if (c == null) {
            throw new IllegalArgumentException("El contacto esta vacio :(, revisa bien :)");
        }

        if (agendaLlena()) {
            throw new IllegalStateException("La agenda esta llena :(");
        }

        if (existeContacto(c)) {
            throw new IllegalStateException(
                    "Ya existe un contacto con el nombre '" + c.getNombre() + " " + c.getApellido() + "'.");
        }

        return contactos.add(c);
    }

    // Retorna una copia ordenada alfabéticamente
    public List<Contacto> listarContactos() {
        List<Contacto> copia = new ArrayList<>(contactos);

        copia.sort(
                Comparator
                        .comparing(Contacto::getApellido, String.CASE_INSENSITIVE_ORDER)
                        .thenComparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER)
        );

        return copia;
    }

    // Verifica si ya existe el contacto
    public boolean existeContacto(Contacto c) {
        if (c == null) {
            return false;
        }

        return contactos.contains(c);
    }

    // Busca un contacto por nombre y apellido (uso interno)
    private Contacto buscarInterno(String nombre, String apellido) {

        for (Contacto c : contactos) {

            if (c.getNombre().equalsIgnoreCase(nombre)
                    && c.getApellido().equalsIgnoreCase(apellido)) {
                return c;
            }
        }

        return null;
    }

    // Realiza la búsqueda
    public String buscarContacto(String nombre, String apellido) {
        Contacto buscado = buscarInterno(nombre, apellido);
        return buscado != null ? buscado.getTelefono() : null;
    }

    // Borra un registro
    public boolean eliminarContacto(Contacto c) {

        if (c == null) {
            return false;
        }

        return contactos.remove(c);
    }

    // Actualiza el teléfono de un contacto existente
    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {

        Contacto existente = buscarInterno(nombre, apellido);

        if (existente == null) {
            throw new IllegalStateException(
                    "No se encontró el contacto '" + nombre + " " + apellido + "'.");
        }

        // setTelefono valida el formato mediante regex
        existente.setTelefono(nuevoTelefono);

        return true;
    }

    // Indica si la agenda está llena
    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    // Cuántos contactos más se pueden agregar
    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
}