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

    // ========== AGREGAR ==========
    private void agregar() {
        try {
            validarCampos(true);

            if (agenda.agendaLlena()) {
                throw new IllegalStateException("❌ Agenda llena (máximo " + agenda.getCapacidadMaxima() + " contactos)");
            }

            Contacto nuevo = new Contacto(nombre(), apellido(), telefono());
            if (agenda.existeContacto(nuevo)) {
                throw new IllegalStateException("❌ El contacto " + nombre() + " " + apellido() + " ya existe");
            }

            boolean agregado = agenda.añadirContacto(nuevo);
            if (!agregado) {
                throw new IllegalStateException("❌ No se pudo agregar el contacto");
            }

            operacionExitosa("✅ Contacto agregado correctamente");
        } catch (RuntimeException ex) {
            vista.mostrarMensaje(ex.getMessage(), true);
        }
    }

    private void modificar() {
        try {
            exigirSeleccion();

            // ✅ Validar que los campos NO estén vacíos
            if (nombre().isEmpty() || apellido().isEmpty()) {
                throw new IllegalArgumentException("❌ Nombre y apellido son obligatorios");
            }

            // ✅ Validar el teléfono (para modificar SÍ debe ser válido)
            if (!Contacto.validarTelefono(telefono())) {
                throw new IllegalArgumentException("❌ El teléfono no tiene un formato válido (ej: 600123456)");
            }

            // Verificar que el contacto existe
            Contacto contacto = new Contacto(nombre(), apellido(), telefono());
            if (!agenda.existeContacto(contacto)) {
                throw new IllegalStateException("❌ El contacto " + nombre() + " " + apellido() + " no existe");
            }

            boolean modificado = agenda.modificarTelefono(nombre(), apellido(), telefono());
            if (!modificado) {
                throw new IllegalStateException("❌ No se pudo modificar el teléfono");
            }

            operacionExitosa("✅ Teléfono modificado correctamente");
        } catch (RuntimeException ex) {
            vista.mostrarMensaje(ex.getMessage(), true);
        }
    }

    // ========== ELIMINAR ==========
    private void eliminar() {
        try {
            exigirSeleccion();

            // ✅ Validar que los campos NO estén vacíos
            if (nombre().isEmpty() || apellido().isEmpty()) {
                throw new IllegalArgumentException("❌ Nombre y apellido son obligatorios");
            }

            Contacto contacto = new Contacto(nombre(), apellido(), "");
            boolean eliminado = agenda.eliminarContacto(contacto);
            if (!eliminado) {
                throw new IllegalStateException("❌ No se encontró el contacto");
            }
            operacionExitosa("✅ Contacto eliminado correctamente");
        } catch (RuntimeException ex) {
            vista.mostrarMensaje(ex.getMessage(), true);
        }
    }

    // ========== BUSCAR (CORREGIDO) ==========
    private void buscar() {
        try {
            // ✅ Validación específica para búsqueda (SOLO nombre y apellido)
            if (nombre().isEmpty() || apellido().isEmpty()) {
                throw new IllegalArgumentException("❌ Nombre y apellido son obligatorios para buscar");
            }

            String telefonoEncontrado = agenda.buscarContacto(nombre(), apellido());
            if (telefonoEncontrado == null) {
                throw new IllegalStateException("❌ No se encontró el contacto " + nombre() + " " + apellido());
            }

            // ✅ Cargar el teléfono encontrado en el campo
            vista.getTxtTelefono().setText(telefonoEncontrado);
            vista.mostrarMensaje("✅ Contacto encontrado - Teléfono: " + telefonoEncontrado, false);

        } catch (RuntimeException ex) {
            vista.mostrarMensaje(ex.getMessage(), true);
        }
    }

    // ========== VALIDACIONES ==========
    private void validarCampos(boolean validarTelefono) {
        if (nombre().isEmpty() || apellido().isEmpty()) {
            throw new IllegalArgumentException("❌ Nombre y apellido son obligatorios");
        }
        if (validarTelefono && !Contacto.validarTelefono(telefono())) {
            throw new IllegalArgumentException("❌ El teléfono no tiene un formato válido (ej: 600123456)");
        }
    }

    private void exigirSeleccion() {
        if (vista.getTablaContactos().getSelectedRow() < 0) {
            throw new IllegalStateException("❌ Selecciona un contacto de la tabla");
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