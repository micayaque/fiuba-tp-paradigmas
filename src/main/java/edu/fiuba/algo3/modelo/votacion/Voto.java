package edu.fiuba.algo3.modelo.votacion;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class Voto {

    private final Jugador votado;

    public Voto(Jugador votado) {
        this.votado = votado;
    }

    public Jugador votado() {
        return votado;
    }
    
}
