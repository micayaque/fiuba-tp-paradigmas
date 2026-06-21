package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.Fase;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.urna.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class FaseDiurna implements Fase {
    private final Urna urnaDeNominacion;
    private Urna urnaDeVotacion;
    private EstadoVotacionDiurna estado;

    public FaseDiurna(SistemaDeEmpate sistemaDeEmpate) {
        this.estado = new Nominacion();
        this.urnaDeVotacion = new Urna(sistemaDeEmpate);
        this.urnaDeNominacion = new Urna(new EmpateDiurnoSinEliminacion());
    }

    public void recibirNominacion(Jugador nominante, Jugador nominado) {
        nominante.votarComoCiudadano(nominado, this.urnaDeNominacion);
    }

    public List<Jugador> iniciarVotacion() {
        List<Jugador> nominados = this.urnaDeNominacion.candidatosVotados();
        this.estado = new PrimeraVotacion(nominados);
        return nominados;
    }

    public void recibirVoto(Jugador votante, Jugador votado) {
        this.estado.recibirVoto(votante, votado, this.urnaDeVotacion);
    }

    @Override
    public AccionVotacion ejecutarResultadoVotacion() {
        ResultadoVotacion resultado = this.urnaDeVotacion.contarVotos();
        return resultado.resolver();
    }

    @Override
    public void iniciarBallotage(List<Jugador> empatados) {
        this.estado = new VotacionBallotage(empatados);
        this.urnaDeVotacion = new Urna(new EmpateDiurnoSinEliminacion());
    }
}