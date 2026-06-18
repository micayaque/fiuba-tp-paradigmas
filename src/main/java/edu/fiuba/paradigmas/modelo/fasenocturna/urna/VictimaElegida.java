package edu.fiuba.paradigmas.modelo.fasenocturna.urna;

import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionVotacion;
import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.VictimaEliminada;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class VictimaElegida implements ResultadoVotacion {

    private final Jugador victima;

    public VictimaElegida(Jugador ganador) {
        this.victima = ganador;
    }

    @Override
    public AccionVotacion resolver() {
        return new VictimaEliminada(this.victima);
    }
}