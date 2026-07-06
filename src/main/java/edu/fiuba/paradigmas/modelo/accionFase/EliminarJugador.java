package edu.fiuba.paradigmas.modelo.accionFase;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class EliminarJugador implements AccionFase {
    private final Jugador victima;

    public EliminarJugador(Jugador victima) {
        this.victima = victima;
    }

    @Override
    public void ejecutar(Fase fase) {
        this.victima.morir();
    }

    public Jugador victima() {
        return this.victima;
    }
}