package edu.fiuba.paradigmas.modelo.excepciones.rol;

public class ProteccionRepetidaExcepcion extends RuntimeException {
    public ProteccionRepetidaExcepcion(String mensaje) {
        super(mensaje);
    }
}
