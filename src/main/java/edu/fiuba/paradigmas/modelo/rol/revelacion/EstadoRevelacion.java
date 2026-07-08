package edu.fiuba.paradigmas.modelo.rol.revelacion;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Sheriff;

import java.util.List;

public interface EstadoRevelacion {
    void revelar(Sheriff sheriff);

    void esObjetivoPrioritario(Jugador jugador, List<Jugador> objetivos);
}
