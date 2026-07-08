package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeInvestigacion;
import edu.fiuba.paradigmas.modelo.historial.Originador;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Investigar extends AccionJugador implements Originador {
    private final Jugador detective;
    private final Jugador sospechoso;
    private Bando bandoDescubierto;

    public Investigar(Jugador detective, Jugador sospechoso) {
        this.detective = detective;
        this.sospechoso = sospechoso;
    }

    @Override
    public void enVivo() {
        this.bandoDescubierto = this.detective.continuarInvestigacionA(this.sospechoso);
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede investigar.");
    }

    public Bando obtenerResultado() {
        return this.bandoDescubierto;
    }

    @Override
    public Memento guardarEstado() {
        return new MementoDeInvestigacion(this.sospechoso, this.bandoDescubierto);
    }
}