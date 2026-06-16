package edu.fiuba.paradigmas.modelo.excepciones;

public class JugadorMuertoExcepcion extends RuntimeException {
    public JugadorMuertoExcepcion(String message) {
        super(message);
    }
}
