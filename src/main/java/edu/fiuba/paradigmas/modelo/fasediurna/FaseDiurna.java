package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionVotacion;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class FaseDiurna {
    private final Urna urnaDeNominacion;
    private final Urna urnaDeVotacion;

    public FaseDiurna() {

        this.urnaDeNominacion = new Urna();
        this.urnaDeVotacion = new Urna();
    }

    public void recibirNominacion(Jugador nominante, Jugador nominado) {
        nominante.votarA(nominado, this.urnaDeNominacion);
    }

    public List<Jugador> nominados() {
        return urnaDeNominacion.nominados();
    }

    public void recibirVoto(Jugador votante, Jugador votado) {
        votante.votarA(votado, this.urnaDeVotacion);
    }

    public AccionVotacion ejecutarResultadoVotacion() {
        ResultadoVotacion resultado = this.urnaDeVotacion.contarVotos();
        AccionVotacion accion = resultado.resolver();
        return accion;
    }
}