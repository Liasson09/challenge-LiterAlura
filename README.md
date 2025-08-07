# <h1 align="center">Challenge LiterAlura - Catálogo de Libros</h1>

<p align="center">
   <img src="https://img.shields.io/badge/STATUS-TERMINADO-GREEN">
   <img src="https://img.shields.io/badge/Java-17-orange">
   <img src="https://img.shields.io/badge/Spring%20Boot-3.5.4-brightgreen">
   <img src="https://img.shields.io/badge/PostgreSQL-blue">
</p>

## 🔎 Índice
* [Descripción del proyecto](#descripción-del-proyecto)
* [Características de la aplicación](#características-de-la-aplicación)
* [Demostración de la aplicación](#demostración-de-la-aplicación)
* [Herramientas utilizadas](#herramientas-utilizadas)
* [Funcionalidades técnicas destacadas](#funcionalidades-técnicas-destacadas)
* [Autora](#autora)

## 📝 Descripción del proyecto

LiterAlura es una aplicación de consola desarrollada en Java con Spring Boot que permite buscar, almacenar y gestionar información sobre libros utilizando la API pública de **Gutendex**. 

En este proyecto se aplican los siguientes conceptos:
- Consumo de APIs REST
- Persistencia de datos con JPA/Hibernate
- Relaciones entre entidades
- Consultas personalizadas con JPQL
- Programación orientada a objetos

## :hammer: Características de la aplicación

### 📚 **Gestión de Libros**
- **Búsqueda de libros** por título desde la API de Gutendex
- **Almacenamiento automático** en base de datos PostgreSQL
- **Listado completo** de libros registrados
- **Filtrado por idiomas** (español, inglés, francés, portugués)
- **Prevención de duplicados** al guardar libros

### 👥 **Gestión de Autores**
- **Registro automático** de autores al guardar libros
- **Listado de autores** únicos registrados
- **Búsqueda de autores vivos** en un año específico

### 💻 **Interfaz de Usuario**
- **Navegación intuitiva** con opciones numeradas
- **Validación de entrada** y manejo de errores
- **Mensajes informativos** claros y detallados

## 🎬 Demostración de la aplicación

```
***** BUSCADOR DE LIBROS *****

1 - Buscar libro por título
2 - Listar libros registrados  
3 - Listar autores registrados
4 - Listar autores vivos en un determinado año
5 - Listar libros por idioma

0 - Salir

Elija la opción a través de su número:
```

### Ejemplo de búsqueda:
```
Ingrese el nombre del libro que desea buscar:
> Don Quijote

********** LIBRO **********
Título: Don Quijote
Autor: Cervantes Saavedra, Miguel de
Idioma: en
Número de descargas: 12345.0
***************************

Libro guardado en la base de datos
```
## 🛠️ Herramientas utilizadas

### Backend
* **Java 17** - Lenguaje de programación
* **Spring Boot** - Framework principal
* **Spring Data JPA** - Persistencia y gestión de datos
* **Hibernate** - ORM (Object-Relational Mapping)

### Base de datos
* **PostgreSQL** - Sistema de gestión de base de datos

### APIs y librerías
* **Gutendex API** - Fuente de información de libros
* **Jackson** - Procesamiento de JSON
* **Maven** - Gestión de dependencias

### Herramientas de desarrollo
* **IntelliJ IDEA** - IDE recomendado
* **Git** - Control de versiones

### Descripción de componentes principales:

- **Model**: Entidades JPA y DTOs para mapeo de datos
- **Repository**: Interfaz para operaciones de base de datos  
- **Service**: Servicios para consumo de API y conversión de datos
- **Principal**: Lógica de la interfaz de usuario y menú principal

## 🎯 Funcionalidades técnicas destacadas

- **Mapeo JSON a objetos** con Jackson y records
- **Consultas JPQL personalizadas** para filtros específicos
- **Relaciones JPA** OneToMany y ManyToOne entre Libro y Autor
- **Validación de duplicados** antes de persistir datos
- **Manejo de excepciones** para entrada de usuario incorrecta

## 👤 Autora

| [<img src="https://github.com/user-attachments/assets/51d84bdd-f015-4ea8-99a7-88dc8850288c" width=115><br><sub>Milagros Soria</sub>](https://github.com/Liasson09)|
| :---: |

---
<p align="center">
💡 Desarrollado como parte del Challenge de <strong>Alura LATAM</strong>
</p>
