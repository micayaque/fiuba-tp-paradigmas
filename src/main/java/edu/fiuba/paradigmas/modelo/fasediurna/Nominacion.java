package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.votacion.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Nominacion implements EstadoVotacionDiurna {
    @Override
    public void recibirVoto(Jugador votante, Jugador votado, Urna urnaVotacion) {
        votante.votarComoCiudadano(votado, urnaVotacion);
    }
}