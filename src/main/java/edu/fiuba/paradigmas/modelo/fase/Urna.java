package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.excepciones.EmpateMafiosoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.ArrayList;

public class Urna {
    private ArrayList<Voto> votosEmitidos;

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

        ArrayList<Jugador> empatados = new ArrayList<>();
        empatados.add(votoGanador.votado());
        for (int i = 1; i < candidatosVotados.size(); i++) {
            Voto totalCandidato = this.totalVotosPara(candidatosVotados.get(i));
            if (totalCandidato.mayorEstricto(votoGanador)) {
                votoGanador = totalCandidato;
                empatados.clear();
                empatados.add(votoGanador.votado());
            } else if (totalCandidato.empataCon(votoGanador)) {
                empatados.add(totalCandidato.votado());
            }
        }
        if (empatados.size() > 1) {
            throw new EmpateMafiosoExcepcion(empatados);
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