package edu.fiuba.paradigmas.modelo.historial;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Rol;

public class MementoDeEliminacion implements Memento {
    private final Jugador victima;
    private final Rol rolRevelado;

    public MementoDeEliminacion(Jugador victima, Rol rolRevelado) {
        this.victima = victima;
        this.rolRevelado = rolRevelado;
    }

    public Jugador victima() { return this.victima; }
    public Rol rolRevelado() { return this.rolRevelado; }
}