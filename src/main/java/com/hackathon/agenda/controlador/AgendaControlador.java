package com.hackathon.agenda.controlador;

import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.modelo.Contacto;
import com.hackathon.agenda.vista.VentanaPrincipal;

public class AgendaControlador {

    private final Agenda agenda;
    private final VentanaPrincipal vista;

    public AgendaControlador(Agenda agenda, VentanaPrincipal vista) {
        this.agenda = agenda;
        this.vista = vista;
        conectarEventos();
        actualizarVista();
    }

    private void conectarEventos() {
        vista.getBtnAgregar().addActionListener(e -> agregar());
        vista.getBtnModificar().addActionListener(e -> modificar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnLimpiar().addActionListener(e -> vista.limpiarCampos());
    }

    private void agregar() {
        try {
            validarCampos(true);
            agenda.añadirContacto(new Contacto(
                    nombre(), apellido(), telefono()));
            operacionExitosa("Contacto agregado correctamente");
        } catch (RuntimeException ex) {
            vista.mostrarMensaje(ex.getMessage(), true);
        }
    }

    private void modificar() {
        try {
            exigirSeleccion();
            validarCampos(true);
            agenda.modificarTelefono(nombre(), apellido(), telefono());
            operacionExitosa("Telefono modificado correctamente");
        } catch (RuntimeException ex) {
            vista.mostrarMensaje(ex.getMessage(), true);
        }
    }

    private void eliminar() {
        try {
            exigirSeleccion();
            boolean eliminado = agenda.eliminarContacto(
                    new Contacto(nombre(), apellido(), telefono()));
            if (!eliminado) {
                throw new IllegalStateException("No se encontro el contacto");
            }
            operacionExitosa("Contacto eliminado correctamente");
        } catch (RuntimeException ex) {
            vista.mostrarMensaje(ex.getMessage(), true);
        }
    }

    private void buscar() {
        try {
            validarCampos(false);
            String telefonoEncontrado = agenda.buscarContacto(nombre(), apellido());
            if (telefonoEncontrado == null) {
                throw new IllegalStateException("No se encontro el contacto");
            }
            vista.cargarDatosEnFormulario(nombre(), apellido(), telefonoEncontrado);
            vista.mostrarMensaje("Contacto encontrado", false);
        } catch (RuntimeException ex) {
            vista.mostrarMensaje(ex.getMessage(), true);
        }
    }

    private void validarCampos(boolean validarTelefono) {
        if (nombre().isEmpty() || apellido().isEmpty()) {
            throw new IllegalArgumentException("Nombre y apellido son obligatorios");
        }
        if (validarTelefono && !Contacto.validarTelefono(telefono())) {
            throw new IllegalArgumentException("El telefono no tiene un formato valido");
        }
    }

    private void exigirSeleccion() {
        if (vista.getTablaContactos().getSelectedRow() < 0) {
            throw new IllegalStateException("Selecciona un contacto de la tabla");
        }
    }

    private void operacionExitosa(String mensaje) {
        actualizarVista();
        vista.limpiarCampos();
        vista.mostrarMensaje(mensaje, false);
    }

    private void actualizarVista() {
        vista.mostrarContactos(agenda.listarContactos());
        vista.mostrarEspaciosLibres(agenda.espaciosLibres());
    }

    private String nombre() { return vista.getTxtNombre().getText().trim(); }
    private String apellido() { return vista.getTxtApellido().getText().trim(); }
    private String telefono() { return vista.getTxtTelefono().getText().trim(); }
}
