package edu.fiuba.paradigmas.modelo.accionVotacion;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public interface AccionVotacion {
    void ejecutar(Fase fase);
    ResultadoFase generarResultado(List<Jugador> vivosDespues);
}