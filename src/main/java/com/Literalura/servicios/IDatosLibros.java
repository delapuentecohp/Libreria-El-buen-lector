package com.Literalura.servicios;

public interface IDatosLibros {
    <T> T obtenerDatos(String json, Class<T> clase);
}
