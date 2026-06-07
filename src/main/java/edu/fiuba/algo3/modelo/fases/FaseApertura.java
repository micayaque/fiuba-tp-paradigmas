package edu.fiuba.algo3.modelo.fases;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

import java.util.Collection;

public class FaseApertura {

    public void ejecutar(Collection<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.miRol().reconocerComplices(jugador, jugadores);
        }
    }
}
