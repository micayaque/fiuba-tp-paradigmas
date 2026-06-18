package edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class VictimaEliminada implements AccionVotacion {
    private final Jugador victima;

    public VictimaEliminada(Jugador victima) {
        this.victima = victima;
    }

    @Override
    public void ejecutar() {
        this.victima.morir();
    }
}