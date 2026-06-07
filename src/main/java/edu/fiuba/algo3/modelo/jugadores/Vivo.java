package edu.fiuba.algo3.modelo.jugadores;

public class Vivo implements EstadoJugador {

    @Override
    public boolean estaVivo() {
        return true;
    }

    @Override
    public EstadoJugador eliminar() {
        return new Eliminado();
    }
}
