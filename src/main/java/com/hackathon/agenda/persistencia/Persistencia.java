package com.hackathon.agenda.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hackathon.agenda.modelo.Contacto;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistencia en formato JSON
 * Usa la librería Gson para serializar/deserializar
 */
public class Persistencia {
    private static final String ARCHIVO = "contactos.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void guardarContactos(List<Contacto> contactos) {
        try (FileWriter writer = new FileWriter(ARCHIVO)) {
            gson.toJson(contactos, writer);
            System.out.println("💾 " + contactos.size() + " contactos guardados en " + ARCHIVO);
        } catch (IOException e) {
            System.err.println("❌ Error al guardar: " + e.getMessage());
        }
    }

    public static List<Contacto> cargarContactos() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            System.out.println("📄 No se encontró " + ARCHIVO + ". Agenda vacía.");
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(ARCHIVO)) {
            Type tipoLista = new TypeToken<List<Contacto>>(){}.getType();
            List<Contacto> contactos = gson.fromJson(reader, tipoLista);
            if (contactos == null) {
                return new ArrayList<>();
            }
            System.out.println("📂 " + contactos.size() + " contactos cargados desde " + ARCHIVO);
            return contactos;
        } catch (IOException e) {
            System.err.println("❌ Error al cargar: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static boolean existeArchivo() {
        return new File(ARCHIVO).exists();
    }

    public static boolean eliminarArchivo() {
        File archivo = new File(ARCHIVO);
        return archivo.exists() && archivo.delete();
    }

    public static String getRutaArchivo() {
        return new File(ARCHIVO).getAbsolutePath();
    }
}