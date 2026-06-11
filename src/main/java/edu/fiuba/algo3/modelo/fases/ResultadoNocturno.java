package edu.fiuba.algo3.modelo.fases;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class ResultadoNocturno implements ResultadoFase {

    private final Jugador victimaElegida;
    private final Jugador protegido;

    public ResultadoNocturno(Jugador victimaElegida, Jugador protegido) {
        this.victimaElegida = victimaElegida;
        this.protegido = protegido;
    }

    public Jugador victimaElegida() {
        return victimaElegida;
    }

    @Override
    public boolean huboEliminacion() {
        return !this.protegido.equals(victimaElegida);
    }
}