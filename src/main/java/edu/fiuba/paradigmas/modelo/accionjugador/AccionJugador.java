package edu.fiuba.paradigmas.modelo.accionjugador;

public abstract class AccionJugador {
    public abstract void enVivo();
    public abstract void enMuerto();

    public void enProtegido() {
        this.enVivo();
    }
}