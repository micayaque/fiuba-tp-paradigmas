package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.excepciones.fase.FaseIncorrectaExcepcion;
import edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna.EstadoVotacionDiurna;
import edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna.PrimeraVotacion;
import edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna.VotacionBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.urna.Urna;

import java.util.List;

public class FaseDiurna implements Fase {
    private Urna urna;
    private EstadoVotacionDiurna estado;

    public FaseDiurna(SistemaDeEmpate sistemaDeEmpate) {
        this.estado = new PrimeraVotacion();
        this.urna = new Urna(sistemaDeEmpate);
    }

    public List<Jugador> votados() {
        return this.urna.jugadoresVotados();
    }

    @Override
    public void recibirVoto(Jugador votante, Jugador votado) {
        this.estado.recibirVoto(votante, votado, this.urna);
    }

    @Override
    public void recibirProteccion(Jugador medico, Jugador protegido) {
        throw new FaseIncorrectaExcepcion("No se puede proteger durante la Fase Diurna.");
    }

    @Override
    public AccionFase ejecutarResultadoVotacion() {
        AccionFase resultado = this.urna.contarVotos();
        resultado.ejecutar(this);
        return resultado;
    }

    @Override
    public void iniciarBallotage(List<Jugador> empatados) {
        this.estado = new VotacionBallotage(empatados);
        this.urna = new Urna(new EmpateDiurnoSinEliminacion());
    }

    @Override
    public void avanzar(Moderador moderador) {
        moderador.comenzarFaseNocturna();
    }
}
