package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Catan;
import edu.fiuba.algo3.vistas.vistas.VistaIntercambioConLaBanca;
import edu.fiuba.algo3.vistas.vistas.VistaTablero2;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ControladorBanca implements EventHandler<ActionEvent> {
    private final Catan catan;
    private final VistaTablero2 vista;

    public ControladorBanca(Catan catan, VistaTablero2 vista) {
        this.catan = catan;
        this.vista = vista;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        VistaIntercambioConLaBanca ventana = new VistaIntercambioConLaBanca((Stage) vista.getScene().getWindow(), catan);
        ventana.showAndWait();
        vista.actualizarInventario();

    }
}
