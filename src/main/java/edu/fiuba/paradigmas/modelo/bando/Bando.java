package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public abstract class Bando {

    public abstract void intentarVerA(Jugador otroJugador, List<Jugador> conocidos);

    public abstract void vistoPorMafia(Jugador jugador, List<Jugador> conocidos);

}