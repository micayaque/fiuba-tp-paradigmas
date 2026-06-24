package edu.fiuba.paradigmas.modelo.rol.revelacion;

import edu.fiuba.paradigmas.modelo.excepciones.rol.SheriffYaReveladoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Sheriff;

import java.util.List;

public class Revelado implements EstadoRevelacion {

    @Override
    public void revelar(Sheriff sheriff) {
        throw new SheriffYaReveladoExcepcion("El Sheriff solo puede revelarse una vez por partida.");
    }

    @Override
    public void esObjetivoPrioritario(Jugador jugador, List<Jugador> objetivos) {
        objetivos.add(jugador);
    }
}
