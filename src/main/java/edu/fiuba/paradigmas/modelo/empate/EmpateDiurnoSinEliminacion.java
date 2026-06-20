package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.DeclararNocheSinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.urna.Empate;

public class EmpateDiurnoSinEliminacion implements SistemaDeEmpate {

    @Override
    public AccionVotacion resolverEmpate(Empate empate) {
        return new DeclararNocheSinJugadorEliminado();
    }
}