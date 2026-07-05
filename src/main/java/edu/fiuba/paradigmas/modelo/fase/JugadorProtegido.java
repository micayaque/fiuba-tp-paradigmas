package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class JugadorProtegido implements ResultadoFase {
    private final Jugador victimaSalvada;

    public JugadorProtegido(Jugador victimaSalvada) {
        this.victimaSalvada = victimaSalvada;
    }

    public Jugador victimaSalvada() { return this.victimaSalvada; }
}