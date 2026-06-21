package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class RecibirInvestigacion implements AccionJugador {
    private final Jugador sospechoso;
    private Bando bandoRevelado;

    public RecibirInvestigacion(Jugador sospechoso) {
        this.sospechoso = sospechoso;
    }

    @Override
    public void ejecutar() {
        this.bandoRevelado = this.sospechoso.continuarRevelandoIdentidad();
    }

    @Override
    public void rechazar() {
        throw new JugadorMuertoExcepcion("No se puede investigar a un jugador muerto.");
    }

    public Bando obtenerResultado() {
        return this.bandoRevelado;
    }
}