package edu.fiuba.paradigmas.modelo.historial;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class MementoDeBallotage implements Memento {
    private final List<Jugador> empatados;

    public MementoDeBallotage(List<Jugador> empatados) {
        this.empatados = empatados;
    }

    public List<Jugador> empatados() { return List.copyOf(this.empatados); }

}
