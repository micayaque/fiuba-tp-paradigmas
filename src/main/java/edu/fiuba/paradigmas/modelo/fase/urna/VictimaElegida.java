package edu.fiuba.paradigmas.modelo.fase.urna;

import edu.fiuba.paradigmas.modelo.fase.accionMafia.AccionMafia;
import edu.fiuba.paradigmas.modelo.fase.accionMafia.VictimaEliminada;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class VictimaElegida implements ResultadoVotacion {

    private final Jugador ganador;

    public VictimaElegida(Jugador ganador) {
        this.ganador = ganador;
    }

    @Override
    public AccionMafia resolver() {
        return new VictimaEliminada(this.ganador);
    }
}