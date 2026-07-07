package edu.fiuba.paradigmas.modelo.historial;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class MementoDeInvestigacion implements Memento {
    private final Jugador sospechoso;
    private final Bando bandoDescubierto;

    public MementoDeInvestigacion(Jugador sospechoso, Bando bandoDescubierto) {
        this.sospechoso = sospechoso;
        this.bandoDescubierto = bandoDescubierto;
    }

    public Jugador sospechoso() { return this.sospechoso; }
    public Bando bandoDescubierto() { return this.bandoDescubierto; }
}