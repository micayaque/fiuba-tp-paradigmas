package edu.fiuba.paradigmas.modelo.fasenocturna.urna;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import java.util.ArrayList;
import java.util.List;

public class Urna {
    private final ArrayList<Voto> votosEmitidos;

    public Urna() {
        this.votosEmitidos = new ArrayList<>();
    }

    public void agregarVoto(Voto voto) {
        this.votosEmitidos.add(voto);
    }

    public ResultadoVotacion contarVotos() {
        ArrayList<Jugador> candidatosVotados = this.obtenerCandidatosVotados();

        List<Voto> totales = new ArrayList<>();
        for (Jugador c : candidatosVotados) totales.add(this.totalVotosPara(c));

        Voto masVotado = totales.get(0);
        List<Voto> empatados = new ArrayList<>();
        empatados.add(masVotado);
        for (int i = 1; i < totales.size(); i++) {
            Voto actual = totales.get(i);
            if (actual.mayorEstricto(masVotado)) {
                masVotado = actual;
                empatados.clear();
                empatados.add(actual);
            } else if (actual.empataCon(masVotado)) {
                empatados.add(actual);
            }
        }

        if (empatados.size() > 1) return new Empate(this.votosEmitidos);
        
        return new JugadorElegido(masVotado.votado());
    }

    protected Voto totalVotosPara(Jugador victima) {
        Voto total = new Voto(victima, 0);
        for(Voto v : this.votosEmitidos) {
            total = total.acumular(v);
        }
        return total;
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

    public List<Jugador> nominados() {
        return obtenerCandidatosVotados();
    }
}