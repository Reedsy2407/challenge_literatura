package com.example.demo.repository;

import com.example.demo.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepositorio extends JpaRepository<Libro, Long> {
    Optional<Libro> findByGutendexId(Integer gutendexId);
    List<Libro> findByIdioma(String idioma);
    long countByIdioma(String idioma);
}