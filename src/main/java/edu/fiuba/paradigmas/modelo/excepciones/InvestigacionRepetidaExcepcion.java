package edu.fiuba.paradigmas.modelo.excepciones;

public class InvestigacionRepetidaExcepcion extends RuntimeException {
    public InvestigacionRepetidaExcepcion(String mensaje) {
        super(mensaje);
    }
}
