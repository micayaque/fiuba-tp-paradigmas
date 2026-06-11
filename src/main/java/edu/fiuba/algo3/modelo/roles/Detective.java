package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class Detective extends Rol {

    @Override
    public Bando bando() {
        return new Ciudadanos();
    }

    @Override
    public void aceptarAccionNocturna(Jugador propio, TurnoNocturno turno) {
        turno.pedirAccion(propio, this);
    }
}
