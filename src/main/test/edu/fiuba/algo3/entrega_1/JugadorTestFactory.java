package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.mazo.Mazo;
import java.util.ArrayList;
import java.util.List;

public class JugadorTestFactory {

    public static List<Jugador> crearJugadores(int cantidad) {
        List<Rol> roles = new Mazo().generarPara(cantidad);
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            jugadores.add(new Jugador("Jugador " + i, roles.get(i)));
        }
        return jugadores;
    }
}