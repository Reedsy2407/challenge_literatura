package com.example.demo.services;

import com.example.demo.gutendex.ClienteGutendex;
import com.example.demo.gutendex.ResultadoLibroGutendex;
import com.example.demo.model.Autor;
import com.example.demo.model.Libro;
import com.example.demo.repository.AutorRepositorio;
import com.example.demo.repository.LibroRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {
    private final ClienteGutendex clienteGutendex;
    private final LibroRepositorio libroRepositorio;
    private final AutorRepositorio autorRepositorio;

    public LibroServicio(ClienteGutendex clienteGutendex,
                         LibroRepositorio libroRepositorio,
                         AutorRepositorio autorRepositorio) {
        this.clienteGutendex = clienteGutendex;
        this.libroRepositorio = libroRepositorio;
        this.autorRepositorio = autorRepositorio;
    }

    @Transactional
    public String buscarYGuardarPorTitulo(String titulo) {
        long inicioTotal = System.currentTimeMillis();

        System.out.println("Buscando en Gutendex: \"" + titulo + "\" ...");
        long inicioBusqueda = System.currentTimeMillis();

        Optional<ResultadoLibroGutendex> maybe = clienteGutendex.buscarPrimeroPorTitulo(titulo);

        long finBusqueda = System.currentTimeMillis();
        System.out.println("Búsqueda completada en " + (finBusqueda - inicioBusqueda) + " ms.");

        if (maybe.isEmpty()) {
            long finTotal = System.currentTimeMillis();
            System.out.println("Operación finalizada en " + (finTotal - inicioTotal) + " ms.");
            return "No se encontró el libro en Gutendex para: " + titulo;
        }
        ResultadoLibroGutendex g = maybe.get();

        if (libroRepositorio.findByGutendexId(g.getId()).isPresent()) {
            long finTotal = System.currentTimeMillis();
            System.out.println("Operación finalizada en " + (finTotal - inicioTotal) + " ms.");
            return "El libro ya existe en la base (gutendexId=" + g.getId() + ").";
        }

        String nombreAutor;
        Integer nacimiento;
        Integer fallecimiento;
        if (g.getAuthors() != null && !g.getAuthors().isEmpty()) {
            var ga = g.getAuthors().get(0);
            nombreAutor = ga.getName();
            nacimiento = ga.getBirthYear();
            fallecimiento = ga.getDeathYear();
        } else {
            fallecimiento = null;
            nacimiento = null;
            nombreAutor = "Desconocido";
        }

        Optional<Autor> autorExistente = autorRepositorio.findByNombreAndAnioNacimientoAndAnioFallecimiento(nombreAutor, nacimiento, fallecimiento);
        Autor autor = autorExistente.orElseGet(() -> new Autor(nombreAutor, nacimiento, fallecimiento));

        String idioma = "unknown";
        if (g.getLanguages() != null && !g.getLanguages().isEmpty()) {
            idioma = g.getLanguages().get(0);
        }

        System.out.println("Guardando en la base de datos...");
        long inicioGuardado = System.currentTimeMillis();

        Libro libro = new Libro(g.getId(), g.getTitle(), idioma, g.getDownloadCount(), autor);
        libroRepositorio.save(libro);

        long finGuardado = System.currentTimeMillis();
        System.out.println("Guardado completado en " + (finGuardado - inicioGuardado) + " ms.");

        long finTotal = System.currentTimeMillis();
        System.out.println("Operación total completada en " + (finTotal - inicioTotal) + " ms.");

        return "Libro guardado: " + libro.getTitulo() + " (gutendexId=" + libro.getGutendexId() + ")";
    }

    public List<Libro> listarTodos() { return libroRepositorio.findAll(); }
    public List<Libro> buscarPorIdioma(String idioma) { return libroRepositorio.findByIdioma(idioma); }
    public long contarPorIdioma(String idioma) { return libroRepositorio.countByIdioma(idioma); }
}