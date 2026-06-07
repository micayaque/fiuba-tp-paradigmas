package edu.fiuba.algo3.modelo.jugadores;

public class Eliminado implements EstadoJugador {

    @Override
    public boolean estaVivo() {
        return false;
    }

    @Override
    public EstadoJugador eliminar() {
        return this;
    }
}
