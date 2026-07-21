package com.hackathon.agenda.modelo;

import com.hackathon.agenda.persistencia.Persistencia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Agenda {
    private List<Contacto> contactos;
    private int capacidadMaxima;

    public Agenda() {
        this(10);
    }

    public Agenda(int capacidad) {
        this.capacidadMaxima = capacidad;
        this.contactos = new ArrayList<>();
        cargarPersistencia();  // 👈 CARGAR CONTACTOS AL INICIAR
    }

    // 👇 CARGAR CONTACTOS DESDE EL ARCHIVO
    private void cargarPersistencia() {
        List<Contacto> cargados = Persistencia.cargarContactos();
        for (Contacto c : cargados) {
            if (!agendaLlena() && !existeContacto(c)) {
                contactos.add(c);
            }
        }
        System.out.println("📋 Agenda cargada con " + contactos.size() + " contactos");
    }

    // 👇 GUARDAR CONTACTOS EN EL ARCHIVO
    private void guardarPersistencia() {
        Persistencia.guardarContactos(contactos);
    }

    public boolean añadirContacto(Contacto c) {
        if (c == null) return false;
        if (agendaLlena()) return false;
        if (existeContacto(c)) return false;
        if (c.getNombre().isEmpty() || c.getApellido().isEmpty()) return false;
        if (!Contacto.validarTelefono(c.getTelefono())) return false;

        boolean resultado = contactos.add(c);
        if (resultado) {
            guardarPersistencia();  // 👈 GUARDAR DESPUÉS DE AGREGAR
        }
        return resultado;
    }

    public boolean existeContacto(Contacto c) {
        if (c == null) return false;
        return contactos.contains(c);
    }

    public List<Contacto> listarContactos() {
        List<Contacto> lista = new ArrayList<>(contactos);
        Collections.sort(lista, (c1, c2) -> {
            int compare = c1.getApellido().compareToIgnoreCase(c2.getApellido());
            if (compare == 0) {
                return c1.getNombre().compareToIgnoreCase(c2.getNombre());
            }
            return compare;
        });
        return lista;
    }

    public String buscarContacto(String nombre, String apellido) {
        if (nombre == null || apellido == null) return null;
        Contacto buscado = new Contacto(nombre, apellido);
        int index = contactos.indexOf(buscado);
        return index != -1 ? contactos.get(index).getTelefono() : null;
    }

    public boolean eliminarContacto(Contacto c) {
        if (c == null) return false;
        boolean resultado = contactos.remove(c);
        if (resultado) {
            guardarPersistencia();  // 👈 GUARDAR DESPUÉS DE ELIMINAR
        }
        return resultado;
    }

    public boolean modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        if (!Contacto.validarTelefono(nuevoTelefono)) return false;
        Contacto buscado = new Contacto(nombre, apellido);
        int index = contactos.indexOf(buscado);
        if (index != -1) {
            contactos.get(index).setTelefono(nuevoTelefono);
            guardarPersistencia();  // 👈 GUARDAR DESPUÉS DE MODIFICAR
            return true;
        }
        return false;
    }

    public boolean agendaLlena() {
        return contactos.size() >= capacidadMaxima;
    }

    public int espaciosLibres() {
        return capacidadMaxima - contactos.size();
    }

    public int getCantidadContactos() {
        return contactos.size();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void limpiarAgenda() {
        contactos.clear();
        guardarPersistencia();  // 👈 GUARDAR DESPUÉS DE LIMPIAR
    }

    // 👇 MÉTODO PARA GUARDAR MANUALMENTE (POR SI ACASO)
    public void guardarManual() {
        guardarPersistencia();
    }

    // 👇 MÉTODO PARA RECARGAR DESDE EL ARCHIVO
    public void recargarDesdeArchivo() {
        contactos.clear();
        cargarPersistencia();
    }

    @Override
    public String toString() {
        return "Agenda [contactos=" + contactos.size() + "/" + capacidadMaxima + "]";
    }
}