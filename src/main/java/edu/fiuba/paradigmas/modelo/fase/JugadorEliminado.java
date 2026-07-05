package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class JugadorEliminado implements ResultadoFase {
    private final Jugador victima;

    public JugadorEliminado(Jugador victima) {
        this.victima = victima;
    }

    public Jugador victima() { return this.victima; }
}