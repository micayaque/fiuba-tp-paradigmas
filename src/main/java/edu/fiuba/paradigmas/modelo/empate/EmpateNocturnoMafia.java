package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.DeclararNocheSinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.votacion.Empate;
import edu.fiuba.paradigmas.modelo.votacion.Voto;

import java.util.List;

public class EmpateNocturnoMafia implements SistemaDeEmpate {

    @Override
    public AccionVotacion resolverEmpate(Empate empate) {
        AccionVotacion accion = new DeclararNocheSinJugadorEliminado();
        List<Voto> votos = empate.votos();
        for (Voto voto : votos) {
            accion = voto.resolverDesempate(accion);
        }
        return accion;
    }

}