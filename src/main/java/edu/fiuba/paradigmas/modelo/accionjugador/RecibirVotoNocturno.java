package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.voto.Voto;

public class RecibirVotoNocturno extends AccionJugador {
    private final Jugador victima;
    private final Voto voto;
    private final UrnaDeVotacion urnaVotacionDeMafia;

    public RecibirVotoNocturno(Jugador victima, Voto voto, UrnaDeVotacion urnaVotacionDeMafia) {
        this.victima = victima;
        this.voto = voto;
        this.urnaVotacionDeMafia = urnaVotacionDeMafia;
    }

    @Override
    public void enVivo() {
        this.victima.continuarRecibiendoVotoMafioso(this.voto, this.urnaVotacionDeMafia);
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("La mafia intentó votar a un jugador que ya está muerto.");
    }
}