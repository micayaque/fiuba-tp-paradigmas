package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.votacion.Voto;

import java.util.List;

public class Ciudadanos implements Bando {

    @Override
    public void intentarVerA(Jugador otroJugador, List<Jugador> complices) {
        // NULL OBJECT
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
        // NULL OBJECT
    }

    @Override
    public void recibirVotoMafioso(Voto voto, UrnaDeVotacion urnaVotacion) {
        urnaVotacion.agregarVoto(voto);
    }
}