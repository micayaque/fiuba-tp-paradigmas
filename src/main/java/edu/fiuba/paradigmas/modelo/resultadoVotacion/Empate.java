package edu.fiuba.paradigmas.modelo.resultadoVotacion;

import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.voto.Voto;

import java.util.List;

public class Empate implements ResultadoVotacion {
    private final List<Voto> todosLosVotosEmitidos;
    private final List<Jugador> jugadoresEmpatados;
    private final SistemaDeEmpate mecanismo;

    public Empate(List<Voto> todosLosVotos, List<Jugador> empatados, SistemaDeEmpate mecanismo) {
        this.todosLosVotosEmitidos = todosLosVotos;
        this.jugadoresEmpatados = empatados;
        this.mecanismo = mecanismo;
    }

    public List<Jugador> empatados() {
        return this.jugadoresEmpatados;
    }

    public List<Voto> votos() {
        return this.todosLosVotosEmitidos;
    }

    @Override
    public AccionVotacion resolver() {
        return this.mecanismo.resolverEmpate(this);
    }

}