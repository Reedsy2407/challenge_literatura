package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "libros",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"gutendex_id"})})
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gutendex_id", nullable = false, unique = true)
    private Integer gutendexId;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String idioma;

    @Column(name = "cantidad_descargas")
    private Integer cantidadDescargas;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro() {}

    public Libro(Integer gutendexId, String titulo, String idioma, Integer cantidadDescargas, Autor autor) {
        this.gutendexId = gutendexId;
        this.titulo = titulo;
        this.idioma = idioma;
        this.cantidadDescargas = cantidadDescargas;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getGutendexId() { return gutendexId; }
    public void setGutendexId(Integer gutendexId) { this.gutendexId = gutendexId; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Integer getCantidadDescargas() { return cantidadDescargas; }
    public void setCantidadDescargas(Integer cantidadDescargas) { this.cantidadDescargas = cantidadDescargas; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return Objects.equals(id, libro.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", gutendexId=" + gutendexId +
                ", titulo='" + titulo + '\'' +
                ", idioma='" + idioma + '\'' +
                ", cantidadDescargas=" + cantidadDescargas +
                ", autor=" + (autor != null ? autor.getNombre() : "N/A") +
                '}';
    }
}