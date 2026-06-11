package edu.fiuba.algo3.modelo.votacion;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.jugadores.JugadorNulo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VotacionNocturna {

    private final List<Voto> votos = new ArrayList<>();

    public void registrarVoto(Voto voto) {
        votos.add(voto);
    }

    public Jugador resultadoVotacion() {
    return votos.stream()
                    .collect(Collectors.groupingBy(Voto::votado, Collectors.counting()))
                    .entrySet().stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse(JugadorNulo.obtenerInstancia());
    }
}
