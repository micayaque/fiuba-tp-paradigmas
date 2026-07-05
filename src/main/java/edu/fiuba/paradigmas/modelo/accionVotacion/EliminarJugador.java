package edu.fiuba.paradigmas.modelo.accionVotacion;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fase.JugadorEliminado;
import edu.fiuba.paradigmas.modelo.fase.JugadorProtegido;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class EliminarJugador implements AccionVotacion {
    private final Jugador victima;

    public EliminarJugador(Jugador victima) {
        this.victima = victima;
    }

    @Override
    public void ejecutar(Fase fase) {
        this.victima.morir();
    }

    public Jugador victima() {
        return this.victima;
    }

    @Override
    public ResultadoFase generarResultado(List<Jugador> vivosDespues) {
        if (vivosDespues.contains(this.victima)) {
            return new JugadorProtegido(this.victima);
        }
        return new JugadorEliminado(this.victima);
    }
}