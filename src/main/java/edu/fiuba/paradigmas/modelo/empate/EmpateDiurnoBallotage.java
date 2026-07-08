package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.accionFase.IniciarBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.voto.Voto;

import java.util.List;

public class EmpateDiurnoBallotage implements SistemaDeEmpate {

    public AccionFase resolverEmpate(List<Voto> votosEmitidos, List<Jugador> jugadoresEmpatados) {
        return new IniciarBallotage(jugadoresEmpatados);
    }
}