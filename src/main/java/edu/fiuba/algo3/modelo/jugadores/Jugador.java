package edu.fiuba.algo3.modelo.jugadores;

import edu.fiuba.algo3.modelo.roles.Bando;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.votacion.Eleccion;

public class Jugador {

    private final String nombre;
    private final Rol carta;
    private EstadoJugador estado = new Vivo();

    public Jugador(String nombre, Rol carta) {
        this.nombre = nombre;
        this.carta = carta;
    }

    public String nombre() {
        return nombre;
    }

    public Rol miRol() {
        return carta;
    }

    public Bando bando() {
        return carta.bando();
    }

    public boolean conoceElRolDe(Jugador otro) {
        if (otro == this) {
            return true;
        }
        return carta.conoceElRolDe(otro);
    }

    public Eleccion votoNocturno(Jugador objetivo) {
        return carta.votoNocturno(this, objetivo);
    }

    public boolean estaVivo() {
        return estado.estaVivo();
    }

    public void eliminar() {
        this.estado = estado.eliminar();
    }
}
