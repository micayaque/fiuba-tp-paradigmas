package edu.fiuba.algo3.modelo.mazo;

import edu.fiuba.algo3.modelo.roles.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {

    public List<Rol> generarPara(int cantidadJugadores) {

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
        Collections.shuffle(roles);
        return roles;
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

}