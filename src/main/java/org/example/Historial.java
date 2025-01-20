package org.example;
import org.example.Constantes;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class Historial {
    private final List<String> registros;

    public Historial() {
        this.registros = new ArrayList<>();
        cargarHistorial();
    }

    public void agregarRegistro(String registro) {
        String registroConFecha = String.format("[%s] %s",
                LocalDateTime.now(), registro);
        registros.add(registroConFecha);
        guardarHistorial();
    }

    public List<String> obtenerRegistros() {
        return new ArrayList<>(registros);
    }

    private void cargarHistorial() {
        try (BufferedReader lector = new BufferedReader(
                new FileReader(Constantes.ARCHIVO_HISTORIAL))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                registros.add(linea);
            }
        } catch (IOException e) {
            System.out.println("No se encontró historial previo.");
        }
    }

    private void guardarHistorial() {
        try (BufferedWriter escritor = new BufferedWriter(
                new FileWriter(Constantes.ARCHIVO_HISTORIAL))) {
            for (String registro : registros) {
                escritor.write(registro);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }
}
