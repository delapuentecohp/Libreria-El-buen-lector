package com.Literalura.modelo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
@Entity
@Table(name="libros")
@JsonIgnoreProperties(ignoreUnknown = true)
public record RDatosLibros(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY) Long Id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<RDatosAutor> autor,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double numeroDescargas
) {

    @Override
    public String toString() {
        String idioma = "";
        if (Objects.equals(idiomas.get(0), "es")){idioma = "Español";}
        if (Objects.equals(idiomas.get(0), "en")){idioma = "Ingles";}
        if (Objects.equals(idiomas.get(0), "fri")){idioma = "Frances";}
        return  "       Titulo: " + titulo +
                "\t\n       Autor: " + autor.toString().replace("[","").replace("]","") +
                "\t\n       Idioma: " + idioma +
                "\t\n       Numero de Descargas: " + numeroDescargas;
    }
}
