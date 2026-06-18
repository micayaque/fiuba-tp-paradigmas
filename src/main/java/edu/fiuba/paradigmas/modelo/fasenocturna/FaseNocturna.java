package edu.fiuba.paradigmas.modelo.fasenocturna;

import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionVotacion;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.ResultadoVotacion;

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

    public AccionVotacion ejecutarResultadoVotacion() {
        ResultadoVotacion resultado = this.urnaDeMafia.contarVotos();
        AccionVotacion accion = resultado.resolver();
        return accion;
    }
}