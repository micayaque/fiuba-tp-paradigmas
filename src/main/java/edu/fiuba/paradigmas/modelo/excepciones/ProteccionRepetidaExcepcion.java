package edu.fiuba.paradigmas.modelo.excepciones;

public class ProteccionRepetidaExcepcion extends RuntimeException {
    public ProteccionRepetidaExcepcion(String mensaje) {
        super(mensaje);
    }
}
