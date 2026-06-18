package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.List;

public interface SistemaDeEmpate {
    AccionVotacion resolverEmpate(List<Jugador> empatados, List<Voto> todosLosVotos);
}