package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.empate.EmpateNocturnoMafia;
import edu.fiuba.paradigmas.modelo.excepciones.fase.FaseIncorrectaExcepcion;
import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeNoche;
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
    public Memento recibirProteccion(Jugador medico, Jugador protegido) {
        Memento memento = medico.protegerA(protegido);
        return this.envolverResultado(memento);
    }

    @Override
    public AccionFase ejecutarResultadoVotacion() {
        AccionFase resultado = this.urnaVotacionDeMafia.contarVotos();
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

    @Override
    public Memento envolverResultado(Memento resultadoBase) {
        return new MementoDeNoche(resultadoBase);
    }

    @Override
    public Memento recibirInvestigacion(Jugador detective, Jugador sospechoso) {
        Memento memento = detective.investigarA(sospechoso);
        return this.envolverResultado(memento);
    }

    @Override
    public Memento recibirRevelacion(Jugador sheriff) {
        throw new FaseIncorrectaExcepcion("El Sheriff no puede revelarse durante la noche.");
    }
}