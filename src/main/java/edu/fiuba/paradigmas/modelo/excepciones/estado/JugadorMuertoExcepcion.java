package edu.fiuba.paradigmas.modelo.excepciones.estado;

public class JugadorMuertoExcepcion extends RuntimeException {
    public JugadorMuertoExcepcion(String message) {
        super(message);
    }
}
