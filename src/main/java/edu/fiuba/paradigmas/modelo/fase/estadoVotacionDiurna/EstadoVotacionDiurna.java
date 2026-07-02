package edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna;

import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public abstract class EstadoVotacionDiurna {

    public void recibirVoto(Jugador votante, Jugador votado, Urna urnaVotacion) {
        this.validarCandidato(votado);
        votante.votarComoCiudadano(votado, urnaVotacion);
    }

    protected abstract void validarCandidato(Jugador votado);
}
