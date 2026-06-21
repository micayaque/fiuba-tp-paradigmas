package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;

public class RecibirVotoNocturno implements AccionJugador {
    private final Jugador victima;
    private final Voto voto;
    private final Urna urnaDeMafia;

    public RecibirVotoNocturno(Jugador victima, Voto voto, Urna urnaDeMafia) {
        this.victima = victima;
        this.voto = voto;
        this.urnaDeMafia = urnaDeMafia;
    }

    @Override
    public void ejecutar() {
        this.victima.continuarRecibiendoVotoMafioso(this.voto, this.urnaDeMafia);
    }

    @Override
    public void rechazar() {
        throw new JugadorMuertoExcepcion("La mafia intentó votar a un jugador que ya está muerto.");
    }
}