package com.milagros.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "libro_idiomas", joinColumns = @JoinColumn(name = "libro_id"))
    @Column(name = "idioma")
    private List<String> idiomas;
    private Double numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idiomas = datosLibros.idiomas();
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }

    @Override
    public String toString() {
        return "********** LIBRO **********\n" +
                "Título: " + titulo + "\n" +
                "Autor: " + (autor != null ?
                autor.stream().map(Autor::getNombre).collect(Collectors.joining(", ")) :
                "Desconocido") + "\n" +
                "Idioma: " + (idiomas != null ? String.join(", ", idiomas) : "No especificado") + "\n" +
                "Número de descargas: " + numeroDeDescargas + "\n" +
                "***************************\n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        if (autor != null) {
            autor.forEach(a -> a.setLibro(this));
        }
        this.autor = autor;
    }

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
