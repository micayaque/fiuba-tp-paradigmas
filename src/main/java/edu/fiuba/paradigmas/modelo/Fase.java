package edu.fiuba.paradigmas.modelo;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public interface Fase {
    AccionVotacion ejecutarResultadoVotacion();

    void iniciarBallotage(List<Jugador> empatados);

    void cerrar(List<Jugador> jugadores);
}
