package edu.fiuba.paradigmas.modelo.accionVotacion;

import edu.fiuba.paradigmas.modelo.fase.*;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.List;

public class IniciarBallotage implements AccionVotacion {
    private final List<Jugador> empatados;

    public IniciarBallotage(List<Jugador> empatados) {
        this.empatados = empatados;
    }

    @Override
    public void ejecutar(Fase fase) {
        fase.iniciarBallotage(this.empatados);
    }

    @Override
    public ResultadoFase generarResultado(List<Jugador> vivosDespues) {
        return new BallotageIniciado(this.empatados);
    }
}