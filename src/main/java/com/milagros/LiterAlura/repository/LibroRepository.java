package com.milagros.LiterAlura.repository;

import com.milagros.LiterAlura.model.Autor;
import com.milagros.LiterAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdiomasContains(String idioma);

    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autor a")
    List<Autor> listarAutorDistintos();

    @Query("SELECT a FROM Libro l JOIN l.autor a WHERE a.fechaNacimiento <= :anio AND (a.fechaFallecimiento > :anio OR a.fechaFallecimiento IS NULL)")
    List<Autor> buscarAutorVivosEnAnio(@Param("anio") Integer anio);

    //@Query("SELECT l FROM Libro l ORDER BY l.numeroDescargas DESC")
    //List<Libro> findTop10ByOrderByNumeroDescargasDesc();
}
