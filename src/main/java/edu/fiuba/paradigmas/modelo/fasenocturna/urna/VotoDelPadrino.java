package edu.fiuba.paradigmas.modelo.fasenocturna.urna;

import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionMafia;
import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.VictimaEliminada;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class VotoDelPadrino extends Voto {

    public VotoDelPadrino(Jugador victima) {
        super(victima);
    }

    private VotoDelPadrino(Jugador victima, int cantidad) {
        super(victima, cantidad);
    }

    @Override
    public Voto acumular(Voto otro) {
        if (!this.votado().equals(otro.votado())) return this;
        return new VotoDelPadrino(this.votado(), super.cantidad() + otro.cantidad());
    }

    @Override
    protected Voto serAcumuladoPorUnVotoComun(Voto votoComunAnterior) {
        return new VotoDelPadrino(this.votado(), super.cantidad() + votoComunAnterior.cantidad());
    }

    @Override
    public boolean mayorEstricto(Voto otro) {
        if (super.cantidad() == otro.cantidad()) {
            return true;
        }
        return super.cantidad() > otro.cantidad();
    }

    @Override
    public AccionMafia resolverDesempate(AccionMafia sentenciaActual) {
        return new VictimaEliminada(this.votado());
    }
}