package edu.fiuba.paradigmas.modelo.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.resultadoVotacion.Empate;

public interface SistemaDeEmpate {
    AccionVotacion resolverEmpate(Empate empate);
}