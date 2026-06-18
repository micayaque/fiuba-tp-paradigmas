package edu.fiuba.paradigmas.modelo.fasenocturna.urna;

import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionVotacion;
import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.SinJugadorEliminado;

import java.util.List;

public class Empate implements ResultadoVotacion {
    private final List<Voto> todosLosVotosEmitidos;

    public Empate(List<Voto> todosLosVotos) {
        this.todosLosVotosEmitidos = todosLosVotos;
    }

    @Override
    public AccionVotacion resolver() {
        AccionVotacion accion = new SinJugadorEliminado();
        for (Voto voto : this.todosLosVotosEmitidos) {
            accion = voto.resolverDesempate(accion);
        }
        return accion;
    }
}