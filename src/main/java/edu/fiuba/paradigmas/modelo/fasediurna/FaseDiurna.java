package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.urna.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class FaseDiurna implements GestorDeBallotage {
    private final Urna urnaDeNominacion;
    private Urna urnaDeVotacion;
    private EstadoVotacionDiurna estado;

    public FaseDiurna() {
        this.urnaDeNominacion = new Urna();
        this.estado = new PrimeraVotacion();
    }

    public void configurarEstrategiaEmpate(SistemaDeEmpate estrategia) {
        this.urnaDeVotacion = new Urna(estrategia);
    }

    public void recibirNominacion(Jugador nominante, Jugador nominado) {
        nominante.votarA(nominado, this.urnaDeNominacion);
    }

    public List<Jugador> nominados() {
        return urnaDeNominacion.candidatosVotados();
    }

    public void recibirVoto(Jugador votante, Jugador votado) {
        this.estado.recibirVoto(votante, votado, this.urnaDeVotacion);
    }

    public AccionVotacion ejecutarResultadoVotacion() {
        ResultadoVotacion resultado = this.urnaDeVotacion.contarVotos();
        AccionVotacion accion = resultado.resolver();
        return accion;
    }

    @Override
    public void iniciarBallotage(List<Jugador> empatados) {
        this.estado = new VotacionBallotage(empatados);
        configurarEstrategiaEmpate(new EmpateDiurnoSinEliminacion());
    }
}