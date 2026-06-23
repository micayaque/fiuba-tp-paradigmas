package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.votacion.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public abstract class EstadoVotacionDiurna {

    public void recibirVoto(Jugador votante, Jugador votado, Urna urnaVotacion) {
        this.validarCandidato(votado);
        votante.votarComoCiudadano(votado, urnaVotacion);
    }

    protected abstract void validarCandidato(Jugador votado);
}
