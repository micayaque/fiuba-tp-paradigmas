package edu.fiuba.paradigmas.modelo.urna;

import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

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

    @Override
    public AccionVotacion resolver() {
        return this.mecanismo.resolverEmpate(this.jugadoresEmpatados, this.todosLosVotosEmitidos);
    }
}