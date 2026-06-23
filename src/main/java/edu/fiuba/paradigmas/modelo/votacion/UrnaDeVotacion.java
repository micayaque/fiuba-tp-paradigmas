package edu.fiuba.paradigmas.modelo.votacion;

import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.ArrayList;
import java.util.List;

public class UrnaDeVotacion extends Urna {
    private final SistemaDeEmpate mecanismoDeEmpate;

    public UrnaDeVotacion(SistemaDeEmpate mecanismoDeEmpate) {
        super();
        this.mecanismoDeEmpate = mecanismoDeEmpate;
    }

    public ResultadoVotacion contarVotos() {
        List<Jugador> candidatosVotados = this.jugadoresVotados();

        List<Voto> totales = new ArrayList<>();
        for (Jugador c : candidatosVotados) {
            totales.add(this.totalVotosPara(c));
        }

        Voto masVotado = totales.get(0);
        List<Voto> empatados = new ArrayList<>();
        empatados.add(masVotado);
        for (Voto actual : totales.subList(1, totales.size())) {
            if (actual.mayorEstricto(masVotado)) {
                masVotado = actual;
                empatados.clear();
                empatados.add(actual);
            } else if (actual.empataCon(masVotado)) {
                empatados.add(actual);
            }
        }

        if (empatados.size() > 1) {
            List<Jugador> jugadoresEmpatados = new ArrayList<>();
            for (Voto v : empatados) {
                jugadoresEmpatados.add(v.votado());
            }
            return new Empate(this.votosEmitidos, jugadoresEmpatados, this.mecanismoDeEmpate);
        }
        
        return new JugadorElegido(masVotado.votado());
    }

    private Voto totalVotosPara(Jugador victima) {
        Voto total = new Voto(victima, 0);
        for(Voto v : this.votosEmitidos) {
            if(v.votado().equals(victima)) {
                total = total.acumular(v);
            }
        }
        return total;
    }
}