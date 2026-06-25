package edu.fiuba.paradigmas.modelo.accionVotacion;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class EliminarJugador implements AccionVotacion {
    private final Jugador victima;

    public EliminarJugador(Jugador victima) {
        this.victima = victima;
    }

    @Override
    public void ejecutar(Fase fase) {
        this.victima.morir();
    }
}