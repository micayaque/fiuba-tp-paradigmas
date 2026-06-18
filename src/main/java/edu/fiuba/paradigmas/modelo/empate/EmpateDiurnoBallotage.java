package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.fasediurna.GestorDeBallotage;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.IniciarBallotage;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class EmpateDiurnoBallotage implements SistemaDeEmpate {
    private final GestorDeBallotage gestor;

    public EmpateDiurnoBallotage(GestorDeBallotage gestor) {
        this.gestor = gestor;
    }

    @Override
    public AccionVotacion resolverEmpate(List<Jugador> empatados, List<Voto> todosLosVotos) {
        return new IniciarBallotage(this.gestor, empatados);
    }
}