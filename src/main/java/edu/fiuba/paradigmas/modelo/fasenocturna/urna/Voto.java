package edu.fiuba.paradigmas.modelo.fasenocturna.urna;

import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionMafia;
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

    protected int cantidad() {
        return this.cantidad;
    }

    public Voto acumular(Voto otro) {
        if (!this.votado.equals(otro.votado())) return this;
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

    public AccionMafia resolverDesempate(AccionMafia resultadoActual) {
        return resultadoActual;
    }
}