package edu.fiuba.algo3.modelo.mazo;

import edu.fiuba.algo3.modelo.excepciones.CantidadDeJugadoresInvalida;
import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Ciudadano;
import edu.fiuba.algo3.modelo.roles.Detective;
import edu.fiuba.algo3.modelo.roles.Mafioso;
import edu.fiuba.algo3.modelo.roles.Medico;
import edu.fiuba.algo3.modelo.roles.Padrino;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.roles.Sheriff;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    private static final int MINIMO_JUGADORES = 5;
    private static final int MAXIMO_JUGADORES = 12;

    public List<Rol> generarPara(int cantidadJugadores) {
        validarCantidad(cantidadJugadores);

        int mafiososTotales = totalDeMafiosos(cantidadJugadores);
        int padrinos = cantidadJugadores >= 10 ? 1 : 0;
        int mafiosos = mafiososTotales - padrinos;
        int detectives = 1;
        int medicos = 1;
        int sheriffs = cantidadJugadores >= 10 ? 1 : 0;
        int ciudadanos = cantidadJugadores - mafiososTotales - detectives - medicos - sheriffs;

        List<Rol> roles = new ArrayList<>();
        agregar(roles, mafiosos, Mafioso::new);
        agregar(roles, padrinos, Padrino::new);
        agregar(roles, detectives, Detective::new);
        agregar(roles, medicos, Medico::new);
        agregar(roles, sheriffs, Sheriff::new);
        agregar(roles, ciudadanos, Ciudadano::new);
        return roles;
    }

    public void asignarCartas(List<Jugador> jugadores) {
        List<Rol> roles = generarPara(jugadores.size());
        Collections.shuffle(roles);
        for (int i = 0; i < jugadores.size(); i++) {
            jugadores.get(i).asignarCarta(roles.get(i));
        }
    }

    private int totalDeMafiosos(int cantidadJugadores) {
        if (cantidadJugadores <= 6) {
            return 1;
        }
        if (cantidadJugadores <= 9) {
            return 2;
        }
        return 3;
    }

    private void agregar(List<Rol> roles, int cantidad, java.util.function.Supplier<Rol> fabrica) {
        for (int i = 0; i < cantidad; i++) {
            roles.add(fabrica.get());
        }
    }

    private void validarCantidad(int cantidadJugadores) {
        if (cantidadJugadores < MINIMO_JUGADORES || cantidadJugadores > MAXIMO_JUGADORES) {
            throw new CantidadDeJugadoresInvalida(
                    "La cantidad de jugadores debe estar entre " + MINIMO_JUGADORES + " y " + MAXIMO_JUGADORES);
        }
    }
}
