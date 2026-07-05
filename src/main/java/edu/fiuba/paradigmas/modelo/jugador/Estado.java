package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.AccionJugador;

import java.util.List;

public interface Estado {
    void estaVivo(Jugador jugador, List<Jugador> vivos);

    void procesarAccion(AccionJugador accion);

    void eliminarProteccion(Jugador jugador);

    void estaEliminado(Jugador jugador, List<Jugador> eliminados);
}