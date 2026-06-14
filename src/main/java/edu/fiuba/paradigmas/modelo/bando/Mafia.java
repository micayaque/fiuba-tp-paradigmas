package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class Mafia extends Bando {

    @Override
    public void intentarVerA(Jugador otroJugador, List<Jugador> conocidos) {
        otroJugador.vistoPorMafia(conocidos);
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> conocidos) {
        conocidos.add(jugador);
    }

}