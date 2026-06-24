package edu.fiuba.paradigmas.modelo.rol.revelacion;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Sheriff;

import java.util.List;

public class NoRevelado implements EstadoRevelacion {

    @Override
    public void revelar(Sheriff sheriff) {
        sheriff.cambiarEstadoRevelacion(new Revelado());
    }

    @Override
    public void esObjetivoPrioritario(Jugador jugador, List<Jugador> objetivos) {
    }
}
