package edu.fiuba.algo3.modelo.votacion;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

import java.util.HashMap;
import java.util.Map;

public class Conteo {

    private final Map<Jugador, Integer> votosPorJugador = new HashMap<>();

    public void sumarVotoPara(Jugador jugador) {
        votosPorJugador.merge(jugador, 1, Integer::sum);
    }

    public Jugador masVotado() {
        return votosPorJugador.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }
}
