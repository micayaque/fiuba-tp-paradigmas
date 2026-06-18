package edu.fiuba.paradigmas.modelo.fasenocturna.urna;

import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionVotacion;
import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.SinVictimaEliminada;

import java.util.List;

public class Empate implements ResultadoVotacion {
    private final List<Voto> todosLosVotosEmitidos;

    public Empate(List<Voto> todosLosVotos) {
        this.todosLosVotosEmitidos = todosLosVotos;
    }

    @Override
    public AccionVotacion resolver() {
        AccionVotacion resultadoNoche = new SinVictimaEliminada();
        for (Voto voto : this.todosLosVotosEmitidos) {
            resultadoNoche = voto.resolverDesempate(resultadoNoche);
        }
        return resultadoNoche;
    }
}