package com.example.demo.services;

import com.example.demo.model.Autor;
import com.example.demo.repository.AutorRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorServicio {
    private final AutorRepositorio autorRepositorio;
    public AutorServicio(AutorRepositorio autorRepositorio) {
        this.autorRepositorio = autorRepositorio;
    }
    public List<Autor> listarTodos() { return autorRepositorio.findAll(); }
    public List<Autor> buscarVivosEnAnio(int anio) { return autorRepositorio.findVivosEnAnio(anio); }
}