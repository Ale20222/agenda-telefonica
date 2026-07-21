package com.hackathon.agenda.modelo;

import com.hackathon.agenda.vista.VentanaPrincipal;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class AgendaTest {

    private Agenda agenda;

    @BeforeEach

    void setUp() {

        agenda = new Agenda();

    }

    // -------------------- añadirContacto --------------------

    @Test

    void añadirContactoValidoDevuelveTrue() {

        Contacto contacto = new Contacto("Juan", "Perez", "612345678");

        assertTrue(agenda.añadirContacto(contacto));

    }

    @Test

    void añadirContactoNuloLanzaExcepcion() {

        assertThrows(

                IllegalArgumentException.class,

                () -> agenda.añadirContacto(null)

        );

    }

    @Test

    void noPermiteContactoDuplicado() {

        Contacto c1 = new Contacto("Juan", "Perez", "612345678");

        Contacto c2 = new Contacto("juan", "PEREZ", "699999999");

        agenda.añadirContacto(c1);

        assertThrows(

                IllegalStateException.class,

                () -> agenda.añadirContacto(c2)

        );

    }

    // -------------------- capacidad --------------------

    @Test

    void agendaLlenaLanzaExcepcionAlAñadir() {

        Agenda agendaPequena = new Agenda(2);

        agendaPequena.añadirContacto(new Contacto("Ana", "Uno", "611111111"));

        agendaPequena.añadirContacto(new Contacto("Luis", "Dos", "622222222"));

        assertTrue(agendaPequena.agendaLlena());

        assertEquals(0, agendaPequena.espaciosLibres());

        Contacto extra = new Contacto("Pedro", "Tres", "633333333");

        assertThrows(

                IllegalStateException.class,

                () -> agendaPequena.añadirContacto(extra)

        );

    }

    @Test

    void espaciosLibresSeActualizaCorrectamente() {

        assertEquals(10, agenda.espaciosLibres());

        agenda.añadirContacto(

                new Contacto("Ana", "Ruiz", "611111111")

        );

        assertEquals(9, agenda.espaciosLibres());

    }

    @Test

    void getCapacidadMaximaDevuelveElValorConfigurado() {

        Agenda agendaCinco = new Agenda(5);

        assertEquals(5, agendaCinco.getCapacidadMaxima());

    }

    @Test

    void capacidadInvalidaLanzaExcepcion() {

        assertThrows(

                IllegalArgumentException.class,

                () -> new Agenda(0)

        );

        assertThrows(

                IllegalArgumentException.class,

                () -> new Agenda(-3)

        );

    }

    // -------------------- existeContacto --------------------

    @Test

    void existeContactoFuncionaCorrectamente() {

        Contacto contacto = new Contacto(

                "Ana",

                "Ruiz",

                "611222333"

        );

        agenda.añadirContacto(contacto);

        Contacto mismoContacto = new Contacto(

                "ANA",

                "ruiz",

                "699999999"

        );

        assertTrue(agenda.existeContacto(mismoContacto));

    }

    @Test

    void existeContactoNuloDevuelveFalse() {

        assertFalse(agenda.existeContacto(null));

    }

    // -------------------- buscarContacto --------------------

    @Test

    void buscarContactoDevuelveTelefono() {

        agenda.añadirContacto(

                new Contacto("Maria", "Gomez", "634567890")

        );

        assertEquals(

                "634567890",

                agenda.buscarContacto("Maria", "Gomez")

        );

    }

    @Test

    void buscarContactoInexistenteDevuelveNull() {

        assertNull(

                agenda.buscarContacto("No", "Existe")

        );

    }

    // -------------------- eliminarContacto --------------------

    @Test

    void eliminarContactoExistenteDevuelveTrue() {

        Contacto contacto = new Contacto(

                "Pedro",

                "Diaz",

                "645678901"

        );

        agenda.añadirContacto(contacto);

        assertTrue(

                agenda.eliminarContacto(contacto)

        );

        assertFalse(

                agenda.existeContacto(contacto)

        );

    }

    @Test

    void eliminarContactoInexistenteDevuelveFalse() {

        Contacto contacto = new Contacto(

                "Pedro",

                "Diaz",

                "645678901"

        );

        assertFalse(

                agenda.eliminarContacto(contacto)

        );

    }

    @Test

    void eliminarContactoNuloDevuelveFalse() {

        assertFalse(

                agenda.eliminarContacto(null)

        );

    }

    // -------------------- modificarTelefono --------------------

    @Test

    void modificarTelefonoActualizaCorrectamente() {

        agenda.añadirContacto(

                new Contacto("Sofia", "Leon", "656789012")

        );

        assertTrue(

                agenda.modificarTelefono(

                        "Sofia",

                        "Leon",

                        "699000111"

                )

        );

        assertEquals(

                "699000111",

                agenda.buscarContacto("Sofia", "Leon")

        );

    }

    @Test

    void modificarTelefonoContactoInexistenteLanzaExcepcion() {

        assertThrows(

                IllegalStateException.class,

                () -> agenda.modificarTelefono(

                        "No",

                        "Existe",

                        "612345678"

                )

        );

    }

    @Test

    void modificarTelefonoConRegexInvalidoLanzaExcepcion() {

        agenda.añadirContacto(

                new Contacto("Sofia", "Leon", "656789012")

        );

        assertThrows(

                IllegalArgumentException.class,

                () -> agenda.modificarTelefono(

                        "Sofia",

                        "Leon",

                        "12345"

                )

        );

    }

    // -------------------- listarContactos --------------------

    @Test

    void listarContactosOrdenaAlfabeticamentePorApellido() {

        agenda.añadirContacto(new Contacto("Carlos", "Zeta", "611111111"));

        agenda.añadirContacto(new Contacto("Ana", "Alfa", "622222222"));

        agenda.añadirContacto(new Contacto("Luis", "Beta", "633333333"));

        List<Contacto> lista = agenda.listarContactos();

        assertEquals("Alfa", lista.get(0).getApellido());

        assertEquals("Beta", lista.get(1).getApellido());

        assertEquals("Zeta", lista.get(2).getApellido());

    }

    @Test

    void listarContactosNoModificaOrdenInternoOriginal() {

        agenda.añadirContacto(new Contacto("Carlos", "Zeta", "611111111"));

        agenda.añadirContacto(new Contacto("Ana", "Alfa", "622222222"));

        List<Contacto> copia = agenda.listarContactos();

        copia.clear();

        assertEquals(

                2,

                agenda.listarContactos().size()

        );

    }

}
