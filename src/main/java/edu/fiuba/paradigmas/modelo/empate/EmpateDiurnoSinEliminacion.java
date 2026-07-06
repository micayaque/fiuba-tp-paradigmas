package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.DeclararFaseSinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.voto.Voto;

import java.util.List;

public class EmpateDiurnoSinEliminacion implements SistemaDeEmpate {

    @Override
    public AccionVotacion resolverEmpate(List<Voto> votosEmitidos, List<Jugador> jugadoresEmpatados) {
        return new DeclararFaseSinJugadorEliminado();
    }
}