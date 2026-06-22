package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.votacion.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.List;

public class VotacionBallotage implements EstadoVotacionDiurna {
    private final List<Jugador> candidatosValidos;

    public VotacionBallotage(List<Jugador> empatados) {
        this.candidatosValidos = empatados;
    }

    @Override
    public void recibirVoto(Jugador votante, Jugador votado, Urna urnaVotacion) {
        if (!this.candidatosValidos.contains(votado)) {
            throw new VotoInvalidoExcepcion("En el ballotage solo se puede votar a los empatados.");
        }
        votante.votarComoCiudadano(votado, urnaVotacion);
    }
}