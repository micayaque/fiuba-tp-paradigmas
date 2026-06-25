package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.votacion.Urna;

public class VotarComoCiudadano extends AccionJugador {
    private final Jugador votante;
    private final Jugador candidato;
    private final Urna votacion;

    public VotarComoCiudadano(Jugador votante, Jugador candidato, Urna votacion) {
        this.votante = votante;
        this.candidato = candidato;
        this.votacion = votacion;
    }

    @Override
    public void enVivo() {
        this.candidato.recibirVotacionDe(this.votante, this.votacion);
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede votar.");
    }
}