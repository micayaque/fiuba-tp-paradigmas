package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;

public abstract class Rol {

    public abstract Bando bando();

    public Bando bandoAparente() {
        return bando();
    }

    public boolean conoceElRolDe(Jugador otro) {
        return false;
    }

    // Doble despacho: los roles con accion nocturna redefinen este metodo con el
    // mismo cuerpo. No es codigo repetido: al cambiar el tipo estatico de `this`
    // se resuelve la sobrecarga de pedirAccion que corresponde a cada rol.
    public void aceptarAccion(Jugador propio, TurnoNocturno turno) {
        turno.pedirAccion(propio, this);
    }
}
