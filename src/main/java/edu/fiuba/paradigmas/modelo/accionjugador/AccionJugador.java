package edu.fiuba.paradigmas.modelo.accionjugador;

public interface AccionJugador {
    void enVivo();
    void enMuerto();

    default void enProtegido() {
        this.enVivo();
    }
}