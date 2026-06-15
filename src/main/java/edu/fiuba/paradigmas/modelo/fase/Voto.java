package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Voto {
    private int cantidad;
    private Jugador votado;

    public Voto(Jugador victima) {
        this.votado = victima;
        this.cantidad = 1;
    }

    public Voto(Jugador victima, int cantidad) {
        this.votado = victima;
        this.cantidad = cantidad;
    }

    public Voto acumular(Voto otro) {
        if (!this.votado.equals(otro.votado)) return this;
        return new Voto(this.votado, this.cantidad + otro.cantidad);
    }

    public boolean mayorEstricto(Voto otro) {
        return this.cantidad > otro.cantidad;
    }

    public boolean empataCon(Voto otro) {
        return this.cantidad == otro.cantidad;
    }

    public Jugador votado() {
        return this.votado;
    }
}