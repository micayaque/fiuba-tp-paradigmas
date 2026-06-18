package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.SinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class EmpateNocturnoMafia implements SistemaDeEmpate {

    @Override
    public AccionVotacion resolverEmpate(List<Jugador> empatados, List<Voto> todosLosVotos) {
        AccionVotacion accion = new SinJugadorEliminado();

        for (Voto voto : todosLosVotos) {
            accion = voto.resolverDesempate(accion);
        }

        return accion;
    }
}