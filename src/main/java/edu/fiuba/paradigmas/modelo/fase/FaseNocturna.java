package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.fase.accionMafia.AccionMafia;
import edu.fiuba.paradigmas.modelo.fase.urna.ResultadoVotacion;

public class FaseNocturna {
    private final Urna urnaDeMafia;

    public FaseNocturna() {
        this.urnaDeMafia =  new Urna();
    }

    public void recibirVoto(Jugador mafioso, Jugador victimaElegida) {
        mafioso.votarComoMafiosoA(victimaElegida, this.urnaDeMafia);
    }

    public void recibirProteccion(Jugador medico, Jugador protegido) {
        medico.protegerA(protegido);
    }

    public AccionMafia ejecutarResultadoVotacion() {
        ResultadoVotacion resultado = this.urnaDeMafia.contarVotos();
        AccionMafia accion = resultado.resolver();
        return accion;
    }
}