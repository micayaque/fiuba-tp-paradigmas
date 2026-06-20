package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;

import java.util.List;

public class Ciudadanos extends Bando {

    @Override
    public void intentarVerA(Jugador otroJugador, List<Jugador> complices) {
        // NULL OBJECT
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
        // NULL OBJECT
    }

    @Override
    public void recibirVotoMafioso(Voto voto, Urna urna) {
        urna.agregarVoto(voto);
    }
}