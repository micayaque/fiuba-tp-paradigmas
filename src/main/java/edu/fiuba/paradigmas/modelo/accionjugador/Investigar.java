package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Investigar implements AccionJugador {
    private final Jugador detective;
    private final Jugador sospechoso;
    private Bando bandoDescubierto;

    public Investigar(Jugador detective, Jugador sospechoso) {
        this.detective = detective;
        this.sospechoso = sospechoso;
    }

    @Override
    public void ejecutar() {
        this.bandoDescubierto = this.detective.continuarInvestigacionA(this.sospechoso);
    }

    @Override
    public void rechazar() {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede investigar.");
    }

    public Bando obtenerResultado() {
        return this.bandoDescubierto;
    }
}