package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;

import java.util.List;

public interface Fase {
    AccionFase ejecutarResultadoVotacion();

    void iniciarBallotage(List<Jugador> empatados);

    void avanzar(Moderador moderador);

    void recibirVoto(Jugador votante, Jugador votado);

    Memento recibirProteccion(Jugador medico, Jugador protegido);

    Memento envolverResultado(Memento resultadoBase);

    Memento recibirInvestigacion(Jugador detective, Jugador sospechoso);

    Memento recibirRevelacion(Jugador sheriff);
}
