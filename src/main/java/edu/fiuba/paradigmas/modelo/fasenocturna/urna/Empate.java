package edu.fiuba.paradigmas.modelo.fasenocturna.urna;

import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionMafia;
import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.NocheSinVictima;

import java.util.List;

public class Empate implements ResultadoVotacion {
    private final List<Voto> todosLosVotosEmitidos;

    public Empate(List<Voto> todosLosVotos) {
        this.todosLosVotosEmitidos = todosLosVotos;
    }

    @Override
    public AccionMafia resolver() {
        AccionMafia resultadoNoche = new NocheSinVictima();
        for (Voto voto : this.todosLosVotosEmitidos) {
            resultadoNoche = voto.resolverDesempate(resultadoNoche);
        }
        return resultadoNoche;
    }
}