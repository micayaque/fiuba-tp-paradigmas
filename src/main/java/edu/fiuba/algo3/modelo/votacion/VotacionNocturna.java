package edu.fiuba.algo3.modelo.votacion;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

import java.util.ArrayList;
import java.util.List;

public class VotacionNocturna {

    private final List<Eleccion> votos = new ArrayList<>();

    public void registrarVoto(Eleccion voto) {
        votos.add(voto);
    }

    public Jugador resultadoVotacion() {
        Conteo conteo = new Conteo();
        for (Eleccion voto : votos) {
            voto.registrarEn(conteo);
        }
        return conteo.masVotado();
    }
}
