package edu.fiuba.paradigmas.modelo.voto;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.accionFase.EliminarJugador;
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
    public AccionFase resolverDesempate(AccionFase sentenciaActual) {
        return new EliminarJugador(this.votado());
    }
}