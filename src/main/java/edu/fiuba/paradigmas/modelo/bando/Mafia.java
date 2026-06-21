package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.votacion.Voto;

import java.util.List;

public class Mafia implements Bando {

    @Override
    public void intentarVerA(Jugador otroJugador, List<Jugador> complices) {
        otroJugador.vistoPorMafia(complices);
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
        complices.add(jugador);
    }

    @Override
    public void recibirVotoMafioso(Voto voto, UrnaDeVotacion urnaVotacion) {
        throw new VotoInvalidoExcepcion("Un mafioso no puede votar a otro mafioso");
    }
}