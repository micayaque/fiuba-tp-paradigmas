package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.votacion.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public interface EstadoVotacionDiurna {
    void recibirVoto(Jugador votante, Jugador votado, Urna urnaVotacion);
}