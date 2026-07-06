package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;

import java.util.List;

public interface Fase {
    AccionFase ejecutarResultadoVotacion();

    void iniciarBallotage(List<Jugador> empatados);

    void avanzar(Moderador moderador);

    void recibirVoto(Jugador votante, Jugador votado);

    void recibirProteccion(Jugador medico, Jugador protegido);

}
