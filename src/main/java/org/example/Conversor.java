package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.*;
public class Conversor {

    private final HttpClient clienteHttp;
    private final Gson gson;

    public Conversor() {
        this.clienteHttp = HttpClient.newHttpClient();
        this.gson = new Gson();
    }

    public double convertir(Moneda origen, Moneda destino, double cantidad)
            throws Exception {
        String url = Constantes.URL_BASE + origen.getCodigo();

        HttpRequest peticion = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> respuesta = clienteHttp.send(
                peticion, HttpResponse.BodyHandlers.ofString());

        if (respuesta.statusCode() != 200) {
            throw new RuntimeException("Error en la API: " + respuesta.statusCode());
        }

        JsonObject jsonRespuesta = gson.fromJson(respuesta.body(), JsonObject.class);
        JsonObject tasas = jsonRespuesta.getAsJsonObject("conversion_rates");
        double tasa = tasas.get(destino.getCodigo()).getAsDouble();

        return cantidad * tasa;
    }
}
