package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Catan;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControladorConstruirPoblado implements EventHandler<ActionEvent> {

    private final Catan catan;

    public ControladorConstruirPoblado(Catan catan) {
        this.catan = catan;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        System.out.println(">>> Solicitando construir Poblado...");
        // Aquí  la lógica visual de mostrar los vértices en el mapa
        // vista.mostrarVerticesParaConstruir();
        // catan.getManagerTurno()...
    }

}
