package edu.fiuba.paradigmas.modelo.accionVotacion;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fase.FaseSinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class DeclararFaseSinJugadorEliminado implements AccionVotacion {

    @Override
    public void ejecutar(Fase fase) {
    }

    @Override
    public ResultadoFase generarResultado(List<Jugador> vivosDespues) {
        return new FaseSinJugadorEliminado();
    }
}