package edu.fiuba.paradigmas.modelo.excepciones;

public class MedicoImpostorExcepcion extends RuntimeException {
    public MedicoImpostorExcepcion(String mensaje) {
        super(mensaje);
    }
}