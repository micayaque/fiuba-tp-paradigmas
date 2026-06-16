package edu.fiuba.paradigmas.modelo.excepciones;

public class VictimaInvalidaExcepcion extends RuntimeException {
    public VictimaInvalidaExcepcion(String mensaje) {
        super(mensaje);
    }
}
