package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.votacion.Voto;

import java.util.List;

public interface Bando {

    void intentarVerA(Jugador otroJugador, List<Jugador> complices);

    void vistoPorMafia(Jugador jugador, List<Jugador> complices);

    void recibirVotoMafioso(Voto voto, UrnaDeVotacion urnaVotacion);
}