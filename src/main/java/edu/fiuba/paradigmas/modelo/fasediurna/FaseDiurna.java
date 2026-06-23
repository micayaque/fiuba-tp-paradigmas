package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.Fase;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.votacion.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeNominacion;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class FaseDiurna implements Fase {
    private final UrnaDeNominacion urnaDeNominacion;
    private UrnaDeVotacion urnaVotacionDeVotacion;
    private EstadoVotacionDiurna estado;

    public FaseDiurna(SistemaDeEmpate sistemaDeEmpate) {
        this.estado = new Nominacion();
        this.urnaVotacionDeVotacion = new UrnaDeVotacion(sistemaDeEmpate);
        this.urnaDeNominacion = new UrnaDeNominacion();
    }

    public void recibirNominacion(Jugador nominante, Jugador nominado) {
        this.estado.recibirVoto(nominante, nominado, this.urnaDeNominacion);
    }

    public List<Jugador> iniciarVotacion() {
        List<Jugador> nominados = this.urnaDeNominacion.nominados();
        this.estado = new PrimeraVotacion(nominados);
        return nominados;
    }

    public void recibirVoto(Jugador votante, Jugador votado) {
        this.estado.recibirVoto(votante, votado, this.urnaVotacionDeVotacion);
    }

    @Override
    public AccionVotacion ejecutarResultadoVotacion() {
        ResultadoVotacion resultado = this.urnaVotacionDeVotacion.contarVotos();
        return resultado.resolver();
    }

    @Override
    public void iniciarBallotage(List<Jugador> empatados) {
        this.estado = new VotacionBallotage(empatados);
        this.urnaVotacionDeVotacion = new UrnaDeVotacion(new EmpateDiurnoSinEliminacion());
    }

    @Override
    public void cerrar(List<Jugador> jugadores) {
    }
}