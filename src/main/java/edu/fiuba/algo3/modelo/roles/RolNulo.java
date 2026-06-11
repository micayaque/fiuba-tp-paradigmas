package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class RolNulo extends Rol {

    @Override
    public Bando bando() {
        return new SinBando();
    }

    @Override
    public void aceptarAccionNocturna(Jugador jugador, TurnoNocturno turno) {

    }

}