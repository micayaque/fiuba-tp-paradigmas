package edu.fiuba.algo3.modelo.fases;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.votacion.VotacionNocturna;

public class FaseNocturna {

    private final VotacionNocturna votacionDeLaMafia = new VotacionNocturna();
    private Jugador protegido;

    public FaseNocturna laMafiaVotaA(Jugador mafioso, Jugador objetivo) {
        votacionDeLaMafia.registrarVoto(mafioso.votoNocturno(objetivo));
        return this;
    }

    public FaseNocturna elMedicoProtegeA(Jugador objetivo) {
        this.protegido = objetivo;
        return this;
    }

    public ResultadoNocturno ejecutar() {
        ResultadoNocturno resultado =
                new ResultadoNocturno(votacionDeLaMafia.resultadoVotacion(), protegido);
        resultado.aplicar();
        return resultado;
    }
}
