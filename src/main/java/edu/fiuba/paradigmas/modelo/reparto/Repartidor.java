package edu.fiuba.paradigmas.modelo.reparto;

import edu.fiuba.paradigmas.modelo.excepciones.RepartoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Repartidor {

    public List<Jugador> repartir(List<String> nombres, List<Rol> mezclados) {
        if (nombres.size() != mezclados.size()) {
            throw new RepartoInvalidoExcepcion("Debe haber exactamente un rol por cada jugador");
        }

        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < nombres.size(); i++) {
            jugadores.add(new Jugador(nombres.get(i), mezclados.get(i)));
        }
        return jugadores;
    }
}
