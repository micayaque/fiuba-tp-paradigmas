package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;

public class VotarComoCiudadano implements AccionJugador {
    private final Jugador votante;
    private final Jugador candidato;
    private final Urna votacion;

    public VotarComoCiudadano(Jugador votante, Jugador candidato, Urna votacion) {
        this.votante = votante;
        this.candidato = candidato;
        this.votacion = votacion;
    }

    @Override
    public void ejecutar() {
        this.votante.continuarVotacionA(this.candidato, this.votacion);
    }

    @Override
    public void rechazar() {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede votar.");
    }
}