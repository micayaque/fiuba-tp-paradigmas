package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;

import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public abstract class ControladorDeFasePorTurnos {
    protected final ControladorDeJuego orquestador;
    protected final Moderador moderador;
    private final Queue<Jugador> turnosPendientes;
    private boolean faseResuelta = false;

    protected ControladorDeFasePorTurnos(ControladorDeJuego orquestador, Moderador moderador, Queue<Jugador> turnos) {
        this.orquestador = orquestador;
        this.moderador = moderador;
        this.turnosPendientes = turnos;
    }

    protected void avanzarTurno() {
        if (this.faseResuelta) return;

        Jugador jugadorActivo = this.turnosPendientes.poll();
        if (jugadorActivo != null) {
            List<Jugador> elegibles = this.candidatosValidos().stream()
                    .filter(j -> j != jugadorActivo)
                    .collect(Collectors.toList());
            this.configurarTurnoPara(jugadorActivo, elegibles);
        } else {
            this.faseResuelta = true;
            this.resolverFase();
        }
    }

    protected List<Jugador> candidatosValidos() {
        return this.moderador.jugadoresVivos();
    }

    protected abstract void configurarTurnoPara(Jugador jugadorActivo, List<Jugador> elegibles);
    protected abstract void resolverFase();
}