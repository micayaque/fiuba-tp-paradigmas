package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class PrimeraVotacion implements EstadoVotacionDiurna {
    @Override
    public void recibirVoto(Jugador votante, Jugador votado, Urna urna) {
        votante.votarA(votado, urna);
    }
}