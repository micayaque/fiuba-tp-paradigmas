package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.empate.EmpateNocturnoMafia;
import edu.fiuba.paradigmas.modelo.excepciones.fase.FaseIncorrectaExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.urna.Urna;

import java.util.List;

public class FaseNocturna implements Fase {
    private final Urna urnaVotacionDeMafia;

    public FaseNocturna() {
        this.urnaVotacionDeMafia = new Urna(new EmpateNocturnoMafia());
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
        AccionVotacion resultado = this.urnaVotacionDeMafia.contarVotos();
        resultado.ejecutar(this);
        return resultado;
    }

    @Override
    public void iniciarBallotage(List<Jugador> empatados) {
    }

    @Override
    public void avanzar(Moderador moderador) {
        moderador.comenzarFaseDiurna();
    }
}
