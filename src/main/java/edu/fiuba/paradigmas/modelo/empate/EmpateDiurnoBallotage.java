package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.IniciarBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.resultadoVotacion.Empate;

import java.util.List;

public class EmpateDiurnoBallotage implements SistemaDeEmpate {

    public AccionVotacion resolverEmpate(Empate empate) {
        List<Jugador> empatados = empate.empatados();
        return new IniciarBallotage(empatados);
    }
}