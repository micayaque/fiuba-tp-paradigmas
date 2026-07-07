package edu.fiuba.paradigmas.modelo.historial;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class MementoDeProteccion implements Memento {
    private final Jugador jugadorProtegido;

    public MementoDeProteccion(Jugador jugadorProtegido) {
        this.jugadorProtegido = jugadorProtegido;
    }

    public Jugador jugadorProtegido() { return this.jugadorProtegido; }
}