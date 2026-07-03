package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.DeclararNocheSinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.voto.Voto;

import java.util.List;

public class EmpateNocturnoMafia implements SistemaDeEmpate {

    @Override
    public AccionVotacion resolverEmpate(List<Voto> votosEmitidos, List<Jugador> jugadoresEmpatados) {
        AccionVotacion accion = new DeclararNocheSinJugadorEliminado();
        for (Voto voto : votosEmitidos) {
            accion = voto.resolverDesempate(accion);
        }
        return accion;
    }

}