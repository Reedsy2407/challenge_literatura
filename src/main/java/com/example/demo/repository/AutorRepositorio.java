package com.example.demo.repository;

import com.example.demo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepositorio extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombreAndAnioNacimientoAndAnioFallecimiento(String nombre, Integer anioNacimiento, Integer anioFallecimiento);

    @Query("SELECT a FROM Autor a WHERE a.anioNacimiento <= :anio AND (a.anioFallecimiento IS NULL OR a.anioFallecimiento >= :anio)")
    List<Autor> findVivosEnAnio(@Param("anio") int anio);
}