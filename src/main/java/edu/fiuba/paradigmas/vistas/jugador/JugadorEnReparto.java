package edu.fiuba.paradigmas.vistas.jugador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Rol;

public class JugadorEnReparto {
    private final String nombre;
    private final Rol carta;
    private final boolean cartaVisible;

    private JugadorEnReparto(String nombre, Rol carta, boolean cartaVisible) {
        this.nombre = nombre;
        this.carta = carta;
        this.cartaVisible = cartaVisible;
    }

    public static JugadorEnReparto oculta(Jugador jugador) {
        return new JugadorEnReparto(jugador.nombre(), jugador.carta(), false);
    }

    public static JugadorEnReparto visible(Jugador jugador) {
        return new JugadorEnReparto(jugador.nombre(), jugador.carta(), true);
    }

    public String nombre() {
        return this.nombre;
    }

    public String nombreRol() {
        if (!this.cartaVisible) {
            return "";
        }
        return this.carta.nombre();
    }
}