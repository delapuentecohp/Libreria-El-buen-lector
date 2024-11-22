package com.Literalura.principal;
import com.Literalura.modelo.RDatos;
import com.Literalura.modelo.RDatosLibros;
import com.Literalura.servicios.DatosLibros;
import com.Literalura.servicios.ObtenerAPI;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";
    private ObtenerAPI obtenerAPI = new ObtenerAPI();
    private DatosLibros libros = new DatosLibros();
    private Scanner teclado = new Scanner(System.in);

    public void Menu(){
        //mostrar todos los libros
//        var json = obtenerAPI.obtenerAPI(URL_BASE);
//        System.out.println(json);
//        mostrar lo que sale en RDatosLibros.java
//        var datos = libros.obtenerDatos(json, RDatos.class);
//        System.out.println(datos);

        //mostrar top 10 descargas
//         datos.libros().stream()
//                .sorted(Comparator.comparing(RDatosLibros::numeroDescargas).reversed())
//                .limit(10)
//                .map(l->l.titulo().toUpperCase())
//                .forEach(System.out::println);

        //busqueda por nombre
//
        while (true){
            System.out.println("\t-----------------------------------");
            System.out.println("\t| **** "+Variables.fGris+"Libreria El buen Lector"+Variables.b+" ****** |");
            System.out.println("\t---------------------------------------");
            System.out.println("\t|  1.- Buscar Libro por titulo        |");
            System.out.println("\t|  2.- Buscar Libros por autor        |");
            System.out.println("\t|  3.- Buscar Libros por idioma       |");
            System.out.println("\t|  4.- Top 10 libros mas descargados  |");
            System.out.println("\t|  5.- Ver libros guardados           |");
            System.out.println("\t|  0.- Salir                          |");
            System.out.println("\t---------------------------------------");
            System.out.print("\t Ingrese una Opcion: ");
            Scanner ingresarOpcion = new Scanner(System.in);
            char opcion = ingresarOpcion.next().charAt(0);
            switch (opcion){

                //Buscar libro por su nombre
                case '1':
                    System.out.printf("\t Ingrese el nombre del libro: ");
                    var nombreLibro = teclado.nextLine();
                    var jsonLibro = obtenerAPI.obtenerAPI(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
                    var datosBusquedaLibros = libros.obtenerDatos(jsonLibro, RDatos.class);
                    Optional<RDatosLibros> libroBuscado = datosBusquedaLibros.libros().stream()
                            .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                            .findFirst();
                    if (libroBuscado.isPresent()) {
                        System.out.println("\t\n       ----------Libro-----------");
                        System.out.println(libroBuscado.get());
                        System.out.println("\t\n       --------------------------");
                        Funciones.presionarTecla();
                    } else {
                        System.out.println("libro no encontrado");
                    }
                    break;

                // Buscar libros por nombre de autor
                case '2':
                    System.out.printf("\t Ingrese el nombre del autor: ");
                    var nombreAutor = teclado.nextLine();
                    var jsonAutor = obtenerAPI.obtenerAPI(URL_BASE + "?search=" + nombreAutor.replace(" ", "+"));
                    var datosBusquedaAutor = libros.obtenerDatos(jsonAutor, RDatos.class);
                    System.out.println("\t\n     Libros Encontrado \n");
                    datosBusquedaAutor.libros().stream()
                            .map(l -> l.titulo().toUpperCase())
                            .forEach((k) -> {
                                System.out.print("\t  " + k + "\n");
                            });
                    Funciones.presionarTecla();
                    break;

                //Buscar libro por idiomas
                case '3':
                    int x = 0;
                    do {
                        System.out.printf("\t Ingrese el idioma (Español-Ingles-Frances): ");
                        var idioma = teclado.nextLine();
                        var idioma1 = idioma.toUpperCase();
                        if (idioma1.equals("ESPAÑOL")) {
                            idioma1 = "es";
                            x = 1;
                        }
                        if (idioma1.equals("INGLES")) {
                            idioma1 = "en";
                            x = 1;
                        }
                        if (idioma1.equals("FRANCES")) {
                            idioma1 = "fr";
                            x = 1;
                        }

                        if (x == 0) {
                            System.out.println(Variables.rojo+"\t\n     Idioma ingresado es incorrecto. Debe ingresar los idiomas Español, Ingles o Frances.\n"+Variables.b);
                        } else {
                            var jsonIdioma = obtenerAPI.obtenerAPI(URL_BASE + "?languages=" + idioma1);
                            var datosBusquedaIdioma = libros.obtenerDatos(jsonIdioma, RDatos.class);
                            System.out.println("\t\n     Libros Encontrado en " + idioma.toUpperCase() + "  \n");
                            datosBusquedaIdioma.libros().stream()
                                    .map(l -> l.titulo().toUpperCase())
                                    .forEach((k) -> {
                                        System.out.print("\t  " + k + "\n");
                                    });
                            Funciones.presionarTecla();
                        }
                    }while (x == 0);
                    break;

                // Top 10 libros mas descargados
                case '4':
                    var jsonTopDescargas = obtenerAPI.obtenerAPI(URL_BASE);
                    var datosTopDescargas = libros.obtenerDatos(jsonTopDescargas, RDatos.class);
                    System.out.println("\t\n     Top 10 libros mas descargados \n");
                    datosTopDescargas.libros().stream()
                            .sorted(Comparator.comparing(RDatosLibros::numeroDescargas).reversed())
                            .limit(10)
                            .map(l -> l.titulo().toUpperCase())
                            .forEach((k) -> {
                                System.out.print("\t  " + k + "\n");
                            });
                    Funciones.presionarTecla();
                    break;

                case '5':
                    break;

                case '0':
                    System.out.println("\t Cerrando Programa");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\t Opcion incorrecta");
                    break;
            }
        }

    }
}
