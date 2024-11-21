package com.Literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record RDatosLibros(

        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<RDatosAutor> autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDescargas
) {

    @Override
    public String toString() {
        return  "       Titulo: " + titulo +
                "\t\n       Autor: " + autor.toString().replace("[","").replace("]","") +
                "\t\n       Idioma: " + idiomas.toString().replace("[","").replace("]","") +
                "\t\n       Numero de Descargas: " + numeroDescargas;
    }
}
