package edu.fiuba.paradigmas.modelo.historial;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class MementoDeRevelacionDeSheriff implements Memento {
    private final Jugador sheriff;

    public MementoDeRevelacionDeSheriff(Jugador sheriff) {
        this.sheriff = sheriff;
    }

    public Jugador sheriff() { return this.sheriff; }
}