package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.excepciones.VictimaInvalida;
import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.votacion.Eleccion;
import edu.fiuba.algo3.modelo.votacion.Voto;

public class Mafioso extends Rol {

    @Override
    public Bando bando() {
        return new Mafia();
    }

    // Un mafioso reconoce a sus complices por el bando del otro: no se guarda
    // ninguna lista, el conocimiento se deriva cuando se pregunta.
    @Override
    public boolean conoceElRolDe(Jugador otro) {
        return otro.bando().esMafia();
    }

    @Override
    public Eleccion votoNocturno(Jugador votante, Jugador objetivo) {
        if (!objetivo.estaVivo()) {
            throw new VictimaInvalida("La victima debe ser un jugador vivo");
        }
        if (objetivo.bando().esMafia()) {
            throw new VictimaInvalida("La Mafia no puede elegir a otro miembro de la Mafia");
        }
        return new Voto(votante, objetivo);
    }
}
