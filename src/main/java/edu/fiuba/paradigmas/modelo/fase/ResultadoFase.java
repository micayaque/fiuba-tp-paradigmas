package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class ResultadoFase {

    private final Jugador jugadorElegidoPorMafia;

    public ResultadoFase(Jugador jugadorElegidoPorMafia) {
        this.jugadorElegidoPorMafia = jugadorElegidoPorMafia;
    }

    public Jugador jugadorElegidoPorMafia() {
        return this.jugadorElegidoPorMafia;
    }
}
