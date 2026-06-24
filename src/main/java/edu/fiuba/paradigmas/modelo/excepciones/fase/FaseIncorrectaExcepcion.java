package edu.fiuba.paradigmas.modelo.excepciones.fase;

public class FaseIncorrectaExcepcion extends RuntimeException {
    public FaseIncorrectaExcepcion(String mensaje) {
        super(mensaje);
    }
}
