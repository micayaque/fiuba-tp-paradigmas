package edu.fiuba.paradigmas.modelo.urna;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class UrnaDeNominacion extends Urna {

    public UrnaDeNominacion() {
        super();
    }

    public List<Jugador> nominados() {
        return this.jugadoresVotados();
    }
}