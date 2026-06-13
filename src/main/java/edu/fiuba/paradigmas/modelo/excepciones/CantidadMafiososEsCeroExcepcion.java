package edu.fiuba.paradigmas.modelo.excepciones;

public class CantidadMafiososEsCeroExcepcion extends RuntimeException {
    public CantidadMafiososEsCeroExcepcion(String message) {
        super(message);
    }
}
