package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class Medico extends Rol {

    @Override
    public Bando bando() {
        return new Ciudadanos();
    }

    @Override
    public void aceptarAccion(Jugador propio, TurnoNocturno turno) {
        turno.pedirAccion(propio, this);
    }

    // La restriccion de no proteger al mismo jugador dos noches seguidas
    // se incorpora en la Entrega 2 (el Medico recordara su ultima proteccion).
}
