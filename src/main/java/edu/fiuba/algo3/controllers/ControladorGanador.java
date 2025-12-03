package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.PantallaPrincipal;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class ControladorGanador implements EventHandler<ActionEvent> {

    private Stage stage;
    private PantallaPrincipal pantallaPrincipal;

    public ControladorGanador(Stage stagePrincipal, PantallaPrincipal contenedorPrincipal) {
        this.stage = stagePrincipal;
        this.pantallaPrincipal = contenedorPrincipal;
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
