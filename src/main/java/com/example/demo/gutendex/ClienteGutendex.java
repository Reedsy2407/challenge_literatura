package com.example.demo.gutendex;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
public class ClienteGutendex {
    // Tiempo máximo para establecer la conexión
    private final HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String BASE = "https://gutendex.com/books";

    public Optional<ResultadoLibroGutendex> buscarPrimeroPorTitulo(String titulo) {
        try {
            String encoded = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String uri = BASE + "/?search=" + encoded;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    // timeout total para la petición (incluye espera y lectura)
                    .timeout(Duration.ofSeconds(8))
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.err.println("Gutendex devolvió estado: " + response.statusCode());
                return Optional.empty();
            }

            RespuestaGutendex respuesta = objectMapper.readValue(response.body(), RespuestaGutendex.class);
            if (respuesta.getResults() == null || respuesta.getResults().isEmpty()) {
                return Optional.empty();
            }
            return Optional.of(respuesta.getResults().get(0));
        } catch (HttpTimeoutException hte) {
            // petición tardó más del timeout configurado
            System.err.println("Timeout al consultar Gutendex: la petición tardó demasiado.");
            return Optional.empty();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            System.err.println("La petición fue interrumpida.");
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}