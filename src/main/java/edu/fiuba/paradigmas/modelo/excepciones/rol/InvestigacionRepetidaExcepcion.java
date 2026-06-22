package edu.fiuba.paradigmas.modelo.excepciones.rol;

public class InvestigacionRepetidaExcepcion extends RuntimeException {
    public InvestigacionRepetidaExcepcion(String mensaje) {
        super(mensaje);
    }
}
