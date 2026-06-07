package edu.fiuba.algo3.modelo.votacion;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class Voto extends Eleccion {

    private final Jugador votado;

    public Voto(Jugador votante, Jugador votado) {
        super(votante);
        this.votado = votado;
    }

    public Jugador votado() {
        return votado;
    }

    @Override
    public void registrarEn(Conteo conteo) {
        conteo.sumarVotoPara(votado);
    }
}
