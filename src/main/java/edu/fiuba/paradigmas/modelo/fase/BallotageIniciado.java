package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.List;

public class BallotageIniciado implements ResultadoFase {
    private final List<Jugador> candidatos;

    public BallotageIniciado(List<Jugador> candidatos) {
        this.candidatos = candidatos;
    }

    public List<Jugador> candidatos() {
        return this.candidatos;
    }
}