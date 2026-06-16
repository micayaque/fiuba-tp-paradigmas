package edu.fiuba.paradigmas.modelo.moderador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class AccionJugador {
    private final Jugador votante;
    private final Jugador votado;

    public AccionJugador(Jugador votante, Jugador votado) {
        this.votante = votante;
        this.votado = votado;
    }

    public Jugador votante() {
        return this.votante;
    }

    public Jugador votado() {
        return this.votado;
    }
}