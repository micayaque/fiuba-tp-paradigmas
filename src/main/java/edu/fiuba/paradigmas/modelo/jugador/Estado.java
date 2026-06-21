package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.AccionJugador;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.List;

public interface Estado {
    void intentarAccion(AccionJugador accion);
    void recibirAccion(AccionJugador comando);
    void morir(Jugador jugador);
    void serProtegido(Jugador jugador);
    void estaVivo(Jugador jugador, List<Jugador> vivos);
    Rol revelarCarta(Jugador jugador);
}