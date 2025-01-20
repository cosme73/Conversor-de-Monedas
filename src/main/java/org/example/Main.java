package org.example;
import org.example.*;
import java.util.Scanner;
public class Main {
    private final Scanner scanner;
    private final CatalogoMonedas catalogo;
    private final Conversor conversor;
    private final Historial historial;

    public Main() {
        this.scanner = new Scanner(System.in);
        this.catalogo = new CatalogoMonedas();
        this.conversor = new Conversor();
        this.historial = new Historial();
    }

    public void iniciar() {
        mostrarBienvenida();

        while (true) {
            mostrarMenu();
            int opcion = obtenerOpcion();

            switch (opcion) {
                case 1 -> realizarConversion();
                case 2 -> mostrarHistorial();
                case 3 -> mostrarMonedas();
                case 4 -> {
                    System.out.println("¡Gracias por usar el conversor!");
                    return;
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void mostrarBienvenida() {
        System.out.println("=═════════════════════════=");
        System.out.println("   Conversor de Monedas    ");
        System.out.println("=═════════════════════════=");
    }

    private void mostrarMenu() {
        System.out.println("\n1. Convertir moneda");
        System.out.println("2. Ver historial");
        System.out.println("3. Ver monedas disponibles");
        System.out.println("4. Salir");
        System.out.print("\nElija una opción: ");
    }

    private int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void realizarConversion() {
        try {
            System.out.print("\nIngrese código de moneda origen: ");
            Moneda origen = catalogo.buscarPorCodigo(scanner.nextLine());
            if (origen == null) {
                System.out.println("Moneda no encontrada");
                return;
            }

            System.out.print("Ingrese código de moneda destino: ");
            Moneda destino = catalogo.buscarPorCodigo(scanner.nextLine());
            if (destino == null) {
                System.out.println("Moneda no encontrada");
                return;
            }

            System.out.print("Ingrese cantidad: ");
            double cantidad = Double.parseDouble(scanner.nextLine());

            double resultado = conversor.convertir(origen, destino, cantidad);

            String mensaje = String.format("%.2f %s = %.2f %s",
                    cantidad, origen.getCodigo(),
                    resultado, destino.getCodigo());

            System.out.println("\nResultado: " + mensaje);
            historial.agregarRegistro(mensaje);

        } catch (NumberFormatException e) {
            System.out.println("Cantidad inválida");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void mostrarHistorial() {
        var registros = historial.obtenerRegistros();
        if (registros.isEmpty()) {
            System.out.println("\nNo hay conversiones registradas");
            return;
        }

        System.out.println("\n=== Historial de Conversiones ===");
        registros.forEach(System.out::println);
    }

    private void mostrarMonedas() {
        System.out.println("\n=== Monedas Disponibles ===");
        catalogo.obtenerTodas().forEach(System.out::println);
    }

    public static void main(String[] args) {
        new Main().iniciar();
    }}