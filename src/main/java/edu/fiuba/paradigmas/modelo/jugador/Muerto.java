package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.AccionJugador;

import java.util.List;

public class Muerto implements Estado {

    @Override
    public void estaVivo(Jugador jugador, List<Jugador> vivos) {
    }

    @Override
    public void procesarAccion(AccionJugador comando) {
        comando.enMuerto();
    }
}
