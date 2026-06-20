package edu.fiuba.paradigmas.modelo.urna;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.EliminarJugador;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class JugadorElegido implements ResultadoVotacion {

    private final Jugador elegido;

    public JugadorElegido(Jugador ganador) {
        this.elegido = ganador;
    }

    @Override
    public AccionVotacion resolver() {
        return new EliminarJugador(this.elegido);
    }
}