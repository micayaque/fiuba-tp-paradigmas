package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class FaseNocturna {
    private final Urna urnaDeMafia;

    public FaseNocturna(List<Jugador>  jugadores) {
        this.urnaDeMafia =  new Urna();
    }

    public void recibirVoto(Jugador mafioso, Jugador victimaElegida) {
        mafioso.votarComoVictimaA(victimaElegida, this.urnaDeMafia);
    }

    public ResultadoFase ejecutarResultadoVotacion() {
        Jugador elegidoPorMafia = this.urnaDeMafia.jugadorMasVotado();
        elegidoPorMafia.morir();
        return new ResultadoFase(elegidoPorMafia);
    }

    public ResultadoFase ejecutarDesempate(Jugador padrino, Jugador victimaElegida) {
        padrino.desempatarVotacionMafia(victimaElegida);
        return new ResultadoFase(victimaElegida);
    }
}
