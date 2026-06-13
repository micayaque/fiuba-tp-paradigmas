package edu.fiuba.paradigmas.modelo.excepciones;

public class CantidadDeJugadoresInvalidaExcepcion extends RuntimeException {
    public CantidadDeJugadoresInvalidaExcepcion(String mensaje) { 
        super(mensaje);
    }
}
