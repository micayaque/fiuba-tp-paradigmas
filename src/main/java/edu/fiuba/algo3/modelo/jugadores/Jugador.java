package edu.fiuba.algo3.modelo.jugadores;

import edu.fiuba.algo3.modelo.roles.Rol;

public class Jugador {

    private final String nombre;
    private Rol carta;
    private EstadoJugador estado = new Vivo();

    public Jugador(String nombre, Rol carta) {
        this.nombre = nombre;
        this.carta = carta;
    }

    public Rol rol() {
        return this.carta;
    }

    public boolean conoceElRolDe(Jugador otroJugador, Rol otroRol) {
        return otroJugador == this || this.rol().conoceElRolDe(otroRol);
    }

    public boolean estaVivo() {
        return this.estado.estaVivo();
    }

    public void eliminar() {
        this.estado = this.estado.eliminar();
    }

}