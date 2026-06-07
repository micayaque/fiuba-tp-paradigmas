package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;

import java.util.Collection;

public class Medico extends Rol {

    @Override
    public Bando bando() {
        return new Ciudadanos();
    }

    @Override
    public void aceptarAccion(Jugador propio, TurnoNocturno turno) {
        turno.pedirAccion(propio, this);
    }

    public Jugador proteger(Jugador objetivo, Collection<Jugador> vivos) {
        // En la Entrega 1 la proteccion es directa; la restriccion de noches
        // consecutivas se incorpora en la Entrega 2.
        return objetivo;
    }
}
