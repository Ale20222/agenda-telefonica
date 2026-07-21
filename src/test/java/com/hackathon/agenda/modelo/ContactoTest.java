package com.hackathon.agenda.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Contacto
 * EJECUTAR: Haz clic derecho en el archivo → Run 'ContactoTest'
 */
public class ContactoTest {

    // ========== PRUEBAS DEL CONSTRUCTOR ==========

    @Test
    void testCrearContactoValido() {
        Contacto contacto = new Contacto("Juan", "Pérez", "600123456");

        assertEquals("Juan", contacto.getNombre());
        assertEquals("Pérez", contacto.getApellido());
        assertEquals("600123456", contacto.getTelefono());
    }

    @Test
    void testCrearContactoConEspacios() {
        Contacto contacto = new Contacto("  Juan  ", "  Pérez  ", "  600123456  ");

        assertEquals("Juan", contacto.getNombre()); // trim() eliminó espacios
        assertEquals("Pérez", contacto.getApellido());
        assertEquals("600123456", contacto.getTelefono());
    }

    @Test
    void testCrearContactoConEspaciosInternos() {
        Contacto contacto = new Contacto("Juan Carlos", "De la Cruz", "600123456");

        assertEquals("Juan Carlos", contacto.getNombre()); // Mantiene espacios internos
        assertEquals("De la Cruz", contacto.getApellido());
    }

    // ========== PRUEBAS DE VALIDACIÓN DE TELÉFONO ==========

    @Test
    void testValidarTelefonoCorrecto() {
        // Formato sin espacios
        assertTrue(Contacto.validarTelefono("600123456"));
        assertTrue(Contacto.validarTelefono("601234567"));
        assertTrue(Contacto.validarTelefono("612345678"));
        assertTrue(Contacto.validarTelefono("700123456"));
        assertTrue(Contacto.validarTelefono("800123456"));
        assertTrue(Contacto.validarTelefono("900123456"));

        // Formato con espacios
        assertTrue(Contacto.validarTelefono("600 123 456"));
        assertTrue(Contacto.validarTelefono("601 234 567"));

        // Formato con +34
        assertTrue(Contacto.validarTelefono("+34 600 123 456"));
        assertTrue(Contacto.validarTelefono("+34600123456"));
    }

    @Test
    void testValidarTelefonoIncorrecto() {
        // Demasiado corto
        assertFalse(Contacto.validarTelefono("123"));
        assertFalse(Contacto.validarTelefono("60012345")); // 8 dígitos
        assertFalse(Contacto.validarTelefono("6001234567")); // 10 dígitos

        // Contiene letras
        assertFalse(Contacto.validarTelefono("abc"));
        assertFalse(Contacto.validarTelefono("600ABC456"));

        // Empieza con número incorrecto (0-5 no válidos)
        assertFalse(Contacto.validarTelefono("500123456"));
        assertFalse(Contacto.validarTelefono("000123456"));

        // Null
        assertFalse(Contacto.validarTelefono(null));

        // Vacío
        assertFalse(Contacto.validarTelefono(""));
        assertFalse(Contacto.validarTelefono("   "));
    }

    // ========== PRUEBAS DE IGUALDAD ==========

    @Test
    void testIgualdadPorNombreYApellido() {
        Contacto c1 = new Contacto("Juan", "Pérez", "600123456");
        Contacto c2 = new Contacto("juan", "pérez", "699999999");
        Contacto c3 = new Contacto("María", "Pérez", "600123456");
        Contacto c4 = new Contacto("Juan", "Gómez", "600123456");

        // Mismo nombre y apellido (ignora mayúsculas)
        assertEquals(c1, c2);

        // Diferente nombre
        assertNotEquals(c1, c3);

        // Diferente apellido
        assertNotEquals(c1, c4);
    }

    @Test
    void testIgualdadConMayusculasYMinusculas() {
        Contacto c1 = new Contacto("JUAN", "PEREZ", "600123456");
        Contacto c2 = new Contacto("juan", "perez", "699999999");
        Contacto c3 = new Contacto("Juan", "Perez", "600123456");

        assertEquals(c1, c2);
        assertEquals(c1, c3);
        assertEquals(c2, c3);
    }

    @Test
    void testIgualdadConAcentos() {
        Contacto c1 = new Contacto("José", "García", "600123456");
        Contacto c2 = new Contacto("josé", "garcía", "699999999");

        // Nota: Java trata mayúsculas/minúsculas pero NO elimina acentos
        // "José" y "josé" son iguales ignorando mayúsculas
        assertEquals(c1, c2);
    }

    @Test
    void testIgualdadConEspacios() {
        Contacto c1 = new Contacto("Juan", "Pérez", "600123456");
        Contacto c2 = new Contacto("  Juan  ", "  Pérez  ", "699999999");

        assertEquals(c1, c2);
    }

    @Test
    void testNoIgualdadConNull() {
        Contacto c1 = new Contacto("Juan", "Pérez", "600123456");
        assertNotEquals(c1, null);
    }

    @Test
    void testNoIgualdadConOtroTipoDeObjeto() {
        Contacto c1 = new Contacto("Juan", "Pérez", "600123456");
        String texto = "Juan Pérez";
        assertNotEquals(c1, texto);
    }

    // ========== PRUEBAS DE HASHCODE ==========

    @Test
    void testHashCodeConsistente() {
        Contacto c1 = new Contacto("Juan", "Pérez", "600123456");
        Contacto c2 = new Contacto("juan", "pérez", "699999999");

        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void testHashCodeDiferenteParaDistintosContactos() {
        Contacto c1 = new Contacto("Juan", "Pérez", "600123456");
        Contacto c3 = new Contacto("María", "Pérez", "600123456");

        // Si son diferentes, sus hash codes DEBEN ser diferentes (o al menos no iguales)
        assertNotEquals(c1.hashCode(), c3.hashCode());
    }

    @Test
    void testHashCodeConMayusculasYMinusculas() {
        Contacto c1 = new Contacto("JUAN", "PEREZ", "600123456");
        Contacto c2 = new Contacto("juan", "perez", "699999999");

        assertEquals(c1.hashCode(), c2.hashCode());
    }

    // ========== PRUEBAS DE SETTERS ==========

    @Test
    void testSetters() {
        Contacto contacto = new Contacto("", "", "");

        contacto.setNombre("  Ana  ");
        contacto.setApellido("  Martínez  ");
        contacto.setTelefono("  601234567  ");

        assertEquals("Ana", contacto.getNombre());
        assertEquals("Martínez", contacto.getApellido());
        assertEquals("601234567", contacto.getTelefono());
    }

    @Test
    void testSettersConEspaciosInternos() {
        Contacto contacto = new Contacto("", "", "");

        contacto.setNombre("  Ana María  ");
        contacto.setApellido("  De la Torre  ");

        assertEquals("Ana María", contacto.getNombre());
        assertEquals("De la Torre", contacto.getApellido());
    }

    // ========== PRUEBAS DE TOSTRING ==========

    @Test
    void testToString() {
        Contacto contacto = new Contacto("Juan", "Pérez", "600123456");
        String expected = "Juan Pérez - 600123456";

        assertEquals(expected, contacto.toString());
    }

    @Test
    void testToStringConEspacios() {
        Contacto contacto = new Contacto("  Juan  ", "  Pérez  ", "  600123456  ");
        String expected = "Juan Pérez - 600123456";

        assertEquals(expected, contacto.toString());
    }

    // ========== PRUEBAS ADICIONALES DE VALIDACIÓN ==========

    @Test
    void testValidarTelefonoConGuiones() {
        // Los guiones NO son válidos según el regex
        assertFalse(Contacto.validarTelefono("600-123-456"));
        assertFalse(Contacto.validarTelefono("+34-600-123-456"));
    }

    @Test
    void testValidarTelefonoConParentesis() {
        assertFalse(Contacto.validarTelefono("(600)123456"));
        assertFalse(Contacto.validarTelefono("+34 (600) 123 456"));
    }

    @Test
    void testValidarTelefonoConEspaciosMultiple() {
        assertTrue(Contacto.validarTelefono("600 123 456"));
        assertTrue(Contacto.validarTelefono("600  123  456")); // Múltiples espacios
    }

    @Test
    void testValidarTelefonoConEspaciosEnLosExtremos() {
        // El regex no hace trim, así que estos deberían fallar
        assertFalse(Contacto.validarTelefono(" 600123456"));
        assertFalse(Contacto.validarTelefono("600123456 "));
        assertFalse(Contacto.validarTelefono(" 600 123 456 "));
    }
}