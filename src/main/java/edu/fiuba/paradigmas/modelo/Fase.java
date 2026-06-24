package edu.fiuba.paradigmas.modelo;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;

import java.util.List;

public interface Fase {
    AccionVotacion ejecutarResultadoVotacion();

    void iniciarBallotage(List<Jugador> empatados);

    void cerrar(List<Jugador> jugadores);

    String descripcion();

    void avanzar(Moderador moderador);

    void recibirVoto(Jugador votante, Jugador votado);

    void recibirProteccion(Jugador medico, Jugador protegido);

    void recibirNominacion(Jugador nominante, Jugador nominado);

    List<Jugador> iniciarVotacion();
}
