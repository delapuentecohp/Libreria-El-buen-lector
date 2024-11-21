package com.Literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RDatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaNacimiento,
        @JsonAlias("death_year") String fechaFallecimiento
) {

    @Override
    public String toString() {
        return  nombre;
    }
}