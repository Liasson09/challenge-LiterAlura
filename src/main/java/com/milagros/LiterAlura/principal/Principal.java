package com.milagros.LiterAlura.principal;
import com.milagros.LiterAlura.model.Autor;
import com.milagros.LiterAlura.model.Datos;
import com.milagros.LiterAlura.model.DatosLibros;
import com.milagros.LiterAlura.model.Libro;
import com.milagros.LiterAlura.repository.LibroRepository;
import com.milagros.LiterAlura.service.ConsumoAPI;
import com.milagros.LiterAlura.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner scan = new Scanner(System.in);

    @Autowired
    private LibroRepository repository;

    public void muestraElMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ***** BUSCADOR DE LIBROS *****
                    
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    
                    0 - Salir
                    
                    Elige la opción que estás buscando:
                    """;

            System.out.println(menu);
            try {
                opcion = scan.nextInt();
                scan.nextLine();
                switch (opcion) {
                    case 1:
                        buscarLibroWeb();
                        break;
                    case 2:
                        mostrarLibrosRegistrados();
                        break;
                    case 3:
                        mostrarAutoresRegistrados();
                        break;
                    case 4:
                        mostrarAutoresVivos();
                        break;
                    case 5:
                        mostrarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }
            } catch (Exception e) {
                System.out.println("Ingrese un número válido");
                scan.nextLine();
            }
        }
    }

    private void buscarLibroWeb() {
        System.out.println("Ingrese el nombre del libro que desea buscar:");
        var tituloLibro = scan.nextLine();

        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado:");
            System.out.println(libroBuscado.get());

            Optional<Libro> libroExistente = repository.findByTituloContainsIgnoreCase(libroBuscado.get().titulo());

            if (libroExistente.isPresent()) {
                System.out.println("Este libro ya está guardado en la base de datos");
            } else {
                Libro libro = new Libro(libroBuscado.get());
                if (libroBuscado.get().autor() != null && !libroBuscado.get().autor().isEmpty()) {
                    List<Autor> autores = libroBuscado.get().autor().stream()
                            .map(datosAutor -> {
                                Autor autor = new Autor(datosAutor);
                                autor.setLibro(libro);
                                return autor;
                            })
                            .collect(Collectors.toList());
                    libro.setAutor(autores);
                }
                repository.save(libro);
                System.out.println("Libro y autores guardados en la base de datos");
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void mostrarLibrosRegistrados() {
        List<Libro> libros = repository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        } else {
            System.out.println("\n***** LIBROS REGISTRADOS *****");
            libros.forEach(System.out::println);
        }
    }

    private void mostrarAutoresRegistrados() {
        List<Autor> autores = repository.listarAutorDistintos();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
        } else {
            System.out.println("\n***** AUTORES REGISTRADOS *****");
            autores.forEach(System.out::println);
        }
    }

    private void mostrarAutoresVivos() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar:");
        try {
            var anio = scan.nextInt();
            scan.nextLine();

            List<Autor> autoresVivos = repository.buscarAutorVivosEnAnio(anio);

            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + anio);
            } else {
                System.out.println("\n***** AUTORES VIVOS EN " + anio + " *****");
                autoresVivos.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.out.println("Ingrese un año válido");
            scan.nextLine();
        }
    }

    private void mostrarLibrosPorIdioma() {
        var menuIdiomas = """
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """;

        System.out.println(menuIdiomas);
        var idioma = scan.nextLine();

        List<Libro> librosPorIdioma = repository.findByIdiomasContains(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma: " + idioma);
        } else {
            System.out.println("\n***** LIBROS EN " + idioma.toUpperCase() + " *****");
            librosPorIdioma.forEach(System.out::println);
        }
    }
}