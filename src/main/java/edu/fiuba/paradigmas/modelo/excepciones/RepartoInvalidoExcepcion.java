package edu.fiuba.paradigmas.modelo.excepciones;

public class RepartoInvalidoExcepcion extends RuntimeException {
    public RepartoInvalidoExcepcion(String mensaje) {
        super(mensaje);
    }
}
