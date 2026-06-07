package edu.fiuba.algo3.modelo.votacion;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

public abstract class Eleccion {

    protected final Jugador votante;

    protected Eleccion(Jugador votante) {
        this.votante = votante;
    }

    public Jugador votante() {
        return votante;
    }

    public abstract void registrarEn(Conteo conteo);
}
