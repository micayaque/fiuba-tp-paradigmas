package edu.fiuba.paradigmas.modelo.fase.accionMafia;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class VictimaEliminada implements AccionMafia {
    private final Jugador victima;

    public VictimaEliminada(Jugador victima) {
        this.victima = victima;
    }

    @Override
    public void ejecutar() {
        this.victima.morir();
    }
}