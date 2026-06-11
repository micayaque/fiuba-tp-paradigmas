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

    private Map<Jugador, Long> agruparVotos() {
        return votos.stream()
                .collect(Collectors.groupingBy(Voto::votado, Collectors.counting()));
    }

    public List<Jugador> jugadoresMasVotados() {
        Map<Jugador, Long> conteo = agruparVotos();

        Long maxVotos = conteo.values().stream()
                .max(Long::compareTo)
                .orElse(0L);

        return conteo.entrySet().stream()
                .filter(entry -> entry.getValue().equals(maxVotos))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public boolean huboEmpate() {
        return jugadoresMasVotados().size() > 1;
    }

    public Jugador resultadoVotacion() {
        List<Jugador> masVotados = jugadoresMasVotados();

        if (masVotados.size() == 1) {
            return masVotados.get(0);
        }

        return JugadorNulo.obtenerInstancia();
    }
}
