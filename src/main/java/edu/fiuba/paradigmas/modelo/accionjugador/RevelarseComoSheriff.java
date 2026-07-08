package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeRevelacionDeSheriff;
import edu.fiuba.paradigmas.modelo.historial.Originador;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class RevelarseComoSheriff extends AccionJugador implements Originador {
    private final Jugador sheriff;

    public RevelarseComoSheriff(Jugador sheriff) {
        this.sheriff = sheriff;
    }

    @Override
    public void enVivo() {
        this.sheriff.continuarRevelandoseComoSheriff();
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("Un jugador eliminado no puede revelarse como Sheriff.");
    }

    @Override
    public Memento guardarEstado() {
        return new MementoDeRevelacionDeSheriff(sheriff);
    }
}
