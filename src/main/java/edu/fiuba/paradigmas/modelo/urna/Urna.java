package edu.fiuba.paradigmas.modelo.urna;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.DeclararFaseSinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.accionVotacion.EliminarJugador;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.voto.Voto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Urna {
    private SistemaDeEmpate mecanismoDeEmpate;
    private final List<Voto> votosEmitidos;

    public Urna() {
        this.votosEmitidos = new ArrayList<>();
    }

    public Urna(SistemaDeEmpate mecanismoDeEmpate) {
        this.mecanismoDeEmpate = mecanismoDeEmpate;
        this.votosEmitidos = new ArrayList<>();
    }

    public void agregarVoto(Voto voto) {
        this.votosEmitidos.add(voto);
    }

    public List<Jugador> jugadoresVotados() {
        Set<Jugador> unicos = new HashSet<>();
        this.votosEmitidos.forEach(voto -> unicos.add(voto.votado()));
        return new ArrayList<>(unicos);
    }

    public AccionVotacion contarVotos() {
        List<Jugador> candidatosVotados = this.jugadoresVotados();
        if(candidatosVotados.isEmpty()){
            return new DeclararFaseSinJugadorEliminado();
        }

        List<Voto> totales = new ArrayList<>();
        for (Jugador c : candidatosVotados) {
            totales.add(this.totalVotosPara(c));
        }

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

        if (empatados.size() > 1) {
            List<Jugador> jugadoresEmpatados = new ArrayList<>();
            for (Voto v : empatados) {
                jugadoresEmpatados.add(v.votado());
            }
            return this.mecanismoDeEmpate.resolverEmpate(empatados, candidatosVotados);

        }

        return new EliminarJugador(masVotado.votado());
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