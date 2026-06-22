package edu.fiuba.paradigmas.modelo.votacion;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Urna {
    protected final List<Voto> votosEmitidos;

    public Urna() {
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
}