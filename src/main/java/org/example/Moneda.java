package org.example;

public class Moneda {

    private final String codigo;
    private final String nombre;
    private final String pais;

    public Moneda(String codigo, String nombre, String pais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getPais() { return pais; }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", codigo, nombre, pais);
    }
}
