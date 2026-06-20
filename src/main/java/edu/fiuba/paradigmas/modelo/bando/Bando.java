package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;

import java.util.List;

public abstract class Bando {

    public abstract void intentarVerA(Jugador otroJugador, List<Jugador> complices);

    public abstract void vistoPorMafia(Jugador jugador, List<Jugador> complices);

    public abstract void recibirVotoMafioso(Voto voto, Urna urna);
}