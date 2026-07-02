package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.excepciones.fase.FaseIncorrectaExcepcion;
import edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna.EstadoVotacionDiurna;
import edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna.Nominacion;
import edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna.PrimeraVotacion;
import edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna.VotacionBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.resultadoVotacion.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.urna.UrnaDeNominacion;
import edu.fiuba.paradigmas.modelo.urna.UrnaDeVotacion;

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

    @Override
    public void recibirNominacion(Jugador nominante, Jugador nominado) {
        this.estado.recibirVoto(nominante, nominado, this.urnaDeNominacion);
    }


    @Override
    public List<Jugador> iniciarVotacion() {
        List<Jugador> nominados = this.urnaDeNominacion.nominados();
        this.estado = new PrimeraVotacion(nominados);
        return nominados;
    }

    @Override
    public void recibirVoto(Jugador votante, Jugador votado) {
        this.estado.recibirVoto(votante, votado, this.urnaVotacionDeVotacion);
    }

    @Override
    public void recibirProteccion(Jugador medico, Jugador protegido) {
        throw new FaseIncorrectaExcepcion("No se puede proteger durante la Fase Diurna.");
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

    @Override
    public void avanzar(Moderador moderador) {
        moderador.comenzarFaseNocturna();
    }
}
