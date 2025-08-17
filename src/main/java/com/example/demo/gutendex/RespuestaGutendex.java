package com.example.demo.gutendex;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RespuestaGutendex {
    private List<ResultadoLibroGutendex> results;
    public List<ResultadoLibroGutendex> getResults() { return results; }
    public void setResults(List<ResultadoLibroGutendex> results) { this.results = results; }
}