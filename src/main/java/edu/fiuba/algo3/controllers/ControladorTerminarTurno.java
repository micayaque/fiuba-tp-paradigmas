package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Catan;
import edu.fiuba.algo3.vistas.botones.BotonLanzarDados;
import edu.fiuba.algo3.vistas.botones.BotonTerminarTurno;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorTerminarTurno implements EventHandler<ActionEvent> {

    private final Catan catan;
    private final BotonLanzarDados botonLanzar;
    private BotonTerminarTurno botonTerminar;

    // Recibimos las referencias en el constructor
    public ControladorTerminarTurno(Catan catan, BotonLanzarDados botonLanzarASdesbloquear) {
        this.catan = catan;
        this.botonLanzar = botonLanzarASdesbloquear;
    }
    public void setBotonTerminar(BotonTerminarTurno btnTerminar) {
        this.botonTerminar = btnTerminar;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(">>> Fin de turno");

        // Modelo: Avanzar turno
        this.catan.siguienteTurno();

    // Reactivar el botón de lanzar (para el sgte jugador)
        this.botonLanzar.setDisable(false);

        // Resetear estado para el sgte jugador
        if (this.botonTerminar != null) {
            this.botonTerminar.setDisable(true);
        }
    }
}
