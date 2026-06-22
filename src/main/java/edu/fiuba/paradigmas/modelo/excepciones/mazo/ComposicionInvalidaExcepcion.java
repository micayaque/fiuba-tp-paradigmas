package edu.fiuba.paradigmas.modelo.excepciones.mazo;

public class ComposicionInvalidaExcepcion extends RuntimeException {
    public ComposicionInvalidaExcepcion(String mensaje) { 
        super(mensaje);
    }
}
