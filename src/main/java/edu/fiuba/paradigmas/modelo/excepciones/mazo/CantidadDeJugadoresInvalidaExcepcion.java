package edu.fiuba.paradigmas.modelo.excepciones.mazo;

public class CantidadDeJugadoresInvalidaExcepcion extends RuntimeException {
    public CantidadDeJugadoresInvalidaExcepcion(String mensaje) { 
        super(mensaje);
    }
}
