package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;

public abstract class Rol {

    public abstract Bando bando();

    public Bando bandoAparente() {
        return bando();
    }

    public boolean conoceElRolDe(Rol otro) {
        return false;
    }

    public boolean esReconocidoPorMafia() {
        return false;
    }

    public void aceptarAccionNocturna(Jugador propio, TurnoNocturno turno) {
        turno.pedirAccion(propio, this);
    }
}
