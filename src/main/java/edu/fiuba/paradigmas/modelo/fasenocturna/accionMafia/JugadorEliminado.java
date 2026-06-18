package edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class JugadorEliminado implements AccionVotacion {
    private final Jugador victima;

    public JugadorEliminado(Jugador victima) {
        this.victima = victima;
    }

    @Override
    public void ejecutar() {
        this.victima.morir();
    }
}