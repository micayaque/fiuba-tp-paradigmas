package edu.fiuba.paradigmas.modelo.fasenocturna;

import edu.fiuba.paradigmas.modelo.Fase;
import edu.fiuba.paradigmas.modelo.empate.EmpateNocturnoMafia;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.votacion.ResultadoVotacion;

import java.util.List;

public class FaseNocturna implements Fase {
    private final UrnaDeVotacion urnaVotacionDeMafia;

    public FaseNocturna() {
        this.urnaVotacionDeMafia =  new UrnaDeVotacion(new EmpateNocturnoMafia());
    }

    public void recibirVoto(Jugador mafioso, Jugador victimaElegida) {
        mafioso.votarComoMafiosoA(victimaElegida, this.urnaVotacionDeMafia);
    }

    public void recibirProteccion(Jugador medico, Jugador protegido) {
        medico.protegerA(protegido);
    }

    @Override
    public AccionVotacion ejecutarResultadoVotacion() {
        ResultadoVotacion resultado = this.urnaVotacionDeMafia.contarVotos();
        return resultado.resolver();
    }

    @Override
    public void iniciarBallotage(List<Jugador> empatados) {
    }

    @Override
    public void cerrar(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            jugador.finalizarNoche();
        }
    }
}