package edu.fiuba.paradigmas.modelo.fasenocturna;

import edu.fiuba.paradigmas.modelo.Fase;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.empate.EmpateNocturnoMafia;
import edu.fiuba.paradigmas.modelo.excepciones.fase.FaseIncorrectaExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.votacion.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;

import java.util.List;

public class FaseNocturna implements Fase {
    private final UrnaDeVotacion urnaVotacionDeMafia;

    public FaseNocturna() {
        this.urnaVotacionDeMafia = new UrnaDeVotacion(new EmpateNocturnoMafia());
    }

    @Override
    public void recibirVoto(Jugador mafioso, Jugador victimaElegida) {
        mafioso.votarComoMafiosoA(victimaElegida, this.urnaVotacionDeMafia);
    }

    @Override
    public void recibirProteccion(Jugador medico, Jugador protegido) {
        medico.protegerA(protegido);
    }

    @Override
    public void recibirNominacion(Jugador nominante, Jugador nominado) {
        throw new FaseIncorrectaExcepcion("No se puede nominar durante la Fase Nocturna.");
    }

    @Override
    public List<Jugador> iniciarVotacion() {
        throw new FaseIncorrectaExcepcion("No hay votación de nominados durante la Fase Nocturna.");
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

    @Override
    public String descripcion() {
        return "Fase Nocturna";
    }

    @Override
    public void avanzar(Moderador moderador) {
        moderador.comenzarFaseDiurna();
    }
}
