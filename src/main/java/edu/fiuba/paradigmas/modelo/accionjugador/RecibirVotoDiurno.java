package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;

public class RecibirVotoDiurno implements AccionJugador {
    private final Jugador candidato;
    private final Urna votacion;

    public RecibirVotoDiurno(Jugador candidato, Urna votacion) {
        this.candidato = candidato;
        this.votacion = votacion;
    }

    @Override
    public void ejecutar() {
        this.candidato.continuarRecibiendoVotacionDe(this.votacion);
    }

    @Override
    public void rechazar() {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede recibir una votación.");
    }
}