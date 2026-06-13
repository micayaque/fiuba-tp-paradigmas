package edu.fiuba.paradigmas.modelo.excepciones;

public class ComposicionInvalidaExcepcion extends RuntimeException {
    public ComposicionInvalidaExcepcion(String mensaje) { 
        super(mensaje);
    }
}
