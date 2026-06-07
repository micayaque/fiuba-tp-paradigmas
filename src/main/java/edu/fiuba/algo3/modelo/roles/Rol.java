package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;

import java.util.Collection;

public abstract class Rol {

    public abstract Bando bando();

    public Bando bandoAparente() {
        return bando();
    }

    public boolean conoceElRolDe(Jugador otro) {
        return false;
    }

    public void reconocerComplices(Jugador propio, Collection<Jugador> jugadores) {
        // Por defecto un rol no reconoce complices. La Mafia redefine este comportamiento.
    }

    public void aceptarAccion(Jugador propio, TurnoNocturno turno) {
        turno.pedirAccion(propio, this);
    }
}
