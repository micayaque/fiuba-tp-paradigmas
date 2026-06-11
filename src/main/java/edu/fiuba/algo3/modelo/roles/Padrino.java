package edu.fiuba.algo3.modelo.roles;

import java.util.List;
import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class Padrino extends Mafioso {

    public Jugador decidirVotoFinal(List<Jugador> jugadoresMasVotados) {
        return jugadoresMasVotados.get(0);
    }

}
