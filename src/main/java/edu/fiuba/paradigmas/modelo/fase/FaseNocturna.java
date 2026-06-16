package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class FaseNocturna {
    private final Urna urnaDeMafia;

    public FaseNocturna(List<Jugador>  jugadores) {
        this.urnaDeMafia =  new Urna();
    }

    public void recibirVoto(Jugador mafioso, Jugador victimaElegida) {
        mafioso.votarComoMafiosoA(victimaElegida, this.urnaDeMafia);
    }

    public void recibirProteccion(Jugador medico, Jugador protegido) {
        medico.protegerA(protegido);
    }

    public ResultadoFase ejecutarResultadoVotacion() {
        Jugador elegidoPorMafia = this.urnaDeMafia.jugadorMasVotado();
        elegidoPorMafia.morir();
        return new ResultadoFase(elegidoPorMafia);
    }
}
