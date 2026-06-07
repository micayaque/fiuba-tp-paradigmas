package edu.fiuba.algo3.modelo.fases;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class ResultadoNocturno implements ResultadoFase {

    private final Jugador victimaElegida;
    private final Jugador protegido;
    private final boolean huboEliminacion;

    public ResultadoNocturno(Jugador victimaElegida, Jugador protegido, boolean huboEliminacion) {
        this.victimaElegida = victimaElegida;
        this.protegido = protegido;
        this.huboEliminacion = huboEliminacion;
    }

    public Jugador victimaElegida() {
        return victimaElegida;
    }

    public Jugador protegido() {
        return protegido;
    }

    public Jugador jugadorEliminado() {
        return huboEliminacion ? victimaElegida : null;
    }

    @Override
    public boolean huboEliminacion() {
        return huboEliminacion;
    }
}
