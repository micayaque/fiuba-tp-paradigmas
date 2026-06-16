package edu.fiuba.paradigmas.modelo.fase.urna;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.ArrayList;

public class Urna {
    private final ArrayList<Voto> votosEmitidos;

    public Urna() {
        this.votosEmitidos = new ArrayList<>();
    }

    public void agregarVoto(Voto voto) {
        this.votosEmitidos.add(voto);
    }

    protected Voto totalVotosPara(Jugador victima) {
        Voto total = new Voto(victima, 0);
        for(Voto v : this.votosEmitidos) {
            total = total.acumular(v);
        }
        return total;
    }

    public Jugador jugadorMasVotado() {
        ArrayList<Jugador> candidatosVotados = this.obtenerCandidatosVotados();
        Voto votoGanador = this.totalVotosPara(candidatosVotados.get(0));
        for (int i = 1; i < candidatosVotados.size(); i++) {
            Voto totalCandidato = this.totalVotosPara(candidatosVotados.get(i));
            if (totalCandidato.mayorEstricto(votoGanador)) {
                votoGanador = totalCandidato;
            }
        }
        return votoGanador.votado();
    }

    protected ArrayList<Jugador> obtenerCandidatosVotados() {
        ArrayList<Jugador> candidatos = new ArrayList<>();
        for (Voto v : this.votosEmitidos) {
            if (!candidatos.contains(v.votado())) {
                candidatos.add(v.votado());
            }
        }
        return candidatos;
    }
}