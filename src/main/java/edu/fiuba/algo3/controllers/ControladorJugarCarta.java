package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Catan;
import edu.fiuba.algo3.vistas.vistas.VistaTablero2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorJugarCarta implements EventHandler<ActionEvent> {
    private final Catan catan;
    private final VistaTablero2 vista;

    public ControladorJugarCarta(Catan catan, VistaTablero2 vista) {
        this.catan = catan;
        this.vista = vista;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
//        String carta = vista.getCartaSeleccionada(); // Necesitamos crear este getter en la vista
//
//        if (carta != null) {
//            System.out.println("Jugando carta: " + carta);
//            // catan.getManagerTurno().usarCarta(carta);
//            // vista.limpiarSeleccionCarta();
//        } else {
//            System.out.println("¡Debes seleccionar una carta primero!");
//        }
//
//
    }
}
