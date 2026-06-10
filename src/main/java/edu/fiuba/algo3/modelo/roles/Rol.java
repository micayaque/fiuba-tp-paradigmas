package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.votacion.Eleccion;
import edu.fiuba.algo3.modelo.votacion.VotoEnBlanco;

public abstract class Rol {

    public abstract Bando bando();

    public Bando bandoAparente() {
        return bando();
    }

    public boolean conoceElRolDe(Jugador otro) {
        return false;
    }

    // Null Object: un rol sin voto nocturno se abstiene (no suma a ningun jugador).
    public Eleccion votoNocturno(Jugador votante, Jugador objetivo) {
        return new VotoEnBlanco(votante);
    }
}
