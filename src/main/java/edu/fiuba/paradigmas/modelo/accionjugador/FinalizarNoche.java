package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.jugador.Vivo;

public class FinalizarNoche extends AccionJugador {
    private final Jugador jugador;

    public FinalizarNoche(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public void enVivo() {
    }

    @Override
    public void enMuerto() {
    }

    @Override
    public void enProtegido() {
        this.jugador.cambiarEstado(new Vivo());
    }
}
