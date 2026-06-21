package edu.fiuba.paradigmas.modelo.votacion;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Voto {
    private final int cantidad;
    private final Jugador votado;

    public Voto(Jugador victima) {
        this.votado = victima;
        this.cantidad = 1;
    }

    public Voto(Jugador victima, int cantidad) {
        this.votado = victima;
        this.cantidad = cantidad;
    }

    protected int cantidad() {
        return this.cantidad;
    }

    public Voto acumular(Voto otro) {
        return otro.serAcumuladoPorUnVotoComun(this);
    }

    protected Voto serAcumuladoPorUnVotoComun(Voto votoComunAnterior) {
        return new Voto(this.votado, this.cantidad + votoComunAnterior.cantidad());
    }

    public boolean mayorEstricto(Voto otro) {
        return this.cantidad > otro.cantidad;
    }

    public boolean empataCon(Voto otro) {
        return !this.mayorEstricto(otro) && !otro.mayorEstricto(this);
    }

    public Jugador votado() {
        return this.votado;
    }

    public AccionVotacion resolverDesempate(AccionVotacion resultadoActual) {
        return resultadoActual;
    }
}