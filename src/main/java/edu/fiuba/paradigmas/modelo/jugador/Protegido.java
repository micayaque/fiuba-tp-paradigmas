package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.AccionJugador;

public class Protegido extends Vivo {

    @Override
    public void procesarAccion(AccionJugador comando) {

    }

    @Override
    public void eliminarProteccion(Jugador jugador) {
        jugador.cambiarEstado(new Vivo());
    }

}
