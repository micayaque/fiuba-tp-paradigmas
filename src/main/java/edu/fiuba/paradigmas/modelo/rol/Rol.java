package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public abstract class Rol {

    private final Bando bando;

    public Rol(Bando bando) {
        this.bando = bando;
    }

    public abstract void contarseEn(ContadorDeRoles contador);

    public void puedeConocerElRolDe(Jugador otroJugador, List<Jugador> conocidos) {
        this.bando.intentarVerA(otroJugador, conocidos);
    }

    public void vistoPorMafia(Jugador duenio, List<Jugador> conocidos) {
        this.bando.vistoPorMafia(duenio, conocidos);
    }
}