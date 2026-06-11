package edu.fiuba.algo3.modelo.fases;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Detective;
import edu.fiuba.algo3.modelo.roles.Mafioso;
import edu.fiuba.algo3.modelo.roles.Medico;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.roles.Padrino;

public interface TurnoNocturno {

    void pedirAccion(Jugador jugador, Mafioso mafioso);

    void pedirAccion(Jugador jugador, Detective detective);

    void pedirAccion(Jugador jugador, Medico medico);

    void pedirAccion(Jugador jugador, Rol sinAccionNocturna);

    void pedirAccion(Jugador jugador, Padrino padrino);
}
