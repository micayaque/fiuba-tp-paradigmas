package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.votacion.Urna;
import edu.fiuba.paradigmas.modelo.votacion.Voto;

public class RecibirVotoDiurno extends AccionJugador {
    private final Jugador candidato;
    private final Urna votacion;

    public RecibirVotoDiurno(Jugador candidato, Urna votacion) {
        this.candidato = candidato;
        this.votacion = votacion;
    }

    @Override
    public void enVivo() {
        this.votacion.agregarVoto(new Voto(this.candidato));
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede recibir una votación.");
    }
}