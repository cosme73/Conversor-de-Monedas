package org.example;
import java.util.*;
public class CatalogoMonedas {

    private final Map<String, Moneda> monedas;

    public CatalogoMonedas() {
        this.monedas = new HashMap<>();
        inicializarMonedas();
    }

    private void inicializarMonedas() {
        agregarMoneda("USD", "Dólar Estadounidense", "Estados Unidos");
        agregarMoneda("EUR", "Euro", "Unión Europea");
        agregarMoneda("MXN", "Peso Mexicano", "México");
        agregarMoneda("ARS", "Peso Argentino", "Argentina");
        agregarMoneda("CLP", "Peso Chileno", "Chile");
        agregarMoneda("COP", "Peso Colombiano", "Colombia");
        agregarMoneda("PEN", "Sol Peruano", "Perú");
        agregarMoneda("BRL", "Real Brasileño", "Brasil");
    }

    private void agregarMoneda(String codigo, String nombre, String pais) {
        monedas.put(codigo, new Moneda(codigo, nombre, pais));
    }

    public Moneda buscarPorCodigo(String codigo) {
        return monedas.get(codigo.toUpperCase());
    }

    public List<Moneda> obtenerTodas() {
        return new ArrayList<>(monedas.values());
    }
}
