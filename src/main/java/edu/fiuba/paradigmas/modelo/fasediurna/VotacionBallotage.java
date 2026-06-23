package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class VotacionBallotage extends EstadoVotacionDiurna {
    private final List<Jugador> candidatosValidos;

    public VotacionBallotage(List<Jugador> empatados) {
        this.candidatosValidos = empatados;
    }

    @Override
    protected void validarCandidato(Jugador votado) {
        if (!this.candidatosValidos.contains(votado)) {
            throw new VotoInvalidoExcepcion("En el ballotage solo se puede votar a los empatados.");
        }
    }
}
