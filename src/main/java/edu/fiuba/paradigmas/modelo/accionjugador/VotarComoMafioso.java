package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;

public class VotarComoMafioso implements AccionJugador {
    private final Jugador votante;
    private final Jugador victimaElegida;
    private final Urna urnaDeMafia;

    public VotarComoMafioso(Jugador votante, Jugador victimaElegida, Urna urnaDeMafia) {
        this.votante = votante;
        this.victimaElegida = victimaElegida;
        this.urnaDeMafia = urnaDeMafia;
    }

    @Override
    public void ejecutar() {
        this.votante.continuarVotacionMafiosaConCarta(this.victimaElegida, this.urnaDeMafia);
    }

    @Override
    public void rechazar() {
        throw new JugadorMuertoExcepcion("Un jugador muerto intentó votar a otro jugador.");
    }
}