package edu.fiuba.algo3.modelo.jugadores;

import edu.fiuba.algo3.modelo.excepciones.JugadorSinRol;
import edu.fiuba.algo3.modelo.excepciones.JugadorYaTieneRol;
import edu.fiuba.algo3.modelo.roles.Rol;

public class Jugador {

    private final String nombre;
    private Rol carta;
    private EstadoJugador estado = new Vivo();

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public Jugador(String nombre, Rol carta) {
        this.nombre = nombre;
        this.carta = carta;
    }

    public String nombre() {
        return nombre;
    }

    public void asignarCarta(Rol carta) {
        if (this.carta != null) {
            throw new JugadorYaTieneRol(nombre + " ya tiene un rol asignado");
        }
        this.carta = carta;
    }

    public boolean tieneRol() {
        return carta != null;
    }

    public Rol miRol() {
        if (carta == null) {
            throw new JugadorSinRol(nombre + " todavia no tiene un rol asignado");
        }
        return carta;
    }

    public boolean conoceElRolDe(Jugador otro) {
        if (otro == this) {
            return true;
        }
        return miRol().conoceElRolDe(otro);
    }

    public boolean estaVivo() {
        return estado.estaVivo();
    }

    public void eliminar() {
        this.estado = estado.eliminar();
    }
}
