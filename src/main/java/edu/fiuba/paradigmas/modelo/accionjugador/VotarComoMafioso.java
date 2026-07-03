package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;

public class VotarComoMafioso implements AccionJugador {
    private final Jugador votante;
    private final Jugador victimaElegida;
    private final Urna urnaVotacionDeMafia;

    public VotarComoMafioso(Jugador votante, Jugador victimaElegida, Urna urnaVotacionDeMafia) {
        this.votante = votante;
        this.victimaElegida = victimaElegida;
        this.urnaVotacionDeMafia = urnaVotacionDeMafia;
    }

    @Override
    public void enVivo() {
        this.votante.continuarVotacionMafiosaConCarta(this.victimaElegida, this.urnaVotacionDeMafia);
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("Un jugador muerto intentó votar a otro jugador.");
    }
}