package edu.fiuba.paradigmas.modelo.fasenocturna;

import edu.fiuba.paradigmas.modelo.Fase;
import edu.fiuba.paradigmas.modelo.empate.EmpateNocturnoMafia;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.urna.ResultadoVotacion;

import java.util.List;

public class FaseNocturna implements Fase {
    private final Urna urnaDeMafia;

    public FaseNocturna() {
        this.urnaDeMafia =  new Urna(new EmpateNocturnoMafia());
    }

    public void recibirVoto(Jugador mafioso, Jugador victimaElegida) {
        mafioso.votarComoMafiosoA(victimaElegida, this.urnaDeMafia);
    }

    public void recibirProteccion(Jugador medico, Jugador protegido) {
        medico.protegerA(protegido);
    }

    @Override
    public AccionVotacion ejecutarResultadoVotacion() {
        ResultadoVotacion resultado = this.urnaDeMafia.contarVotos();
        return resultado.resolver();
    }

    @Override
    public void iniciarBallotage(List<Jugador> empatados) {
        // Null object
    }
}