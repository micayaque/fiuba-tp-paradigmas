package edu.fiuba.paradigmas.modelo.fasenocturna.urna;

import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionVotacion;
import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.JugadorEliminado;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class JugadorElegido implements ResultadoVotacion {

    private final Jugador elegido;

    public JugadorElegido(Jugador ganador) {
        this.elegido = ganador;
    }

    @Override
    public AccionVotacion resolver() {
        return new JugadorEliminado(this.elegido);
    }
}