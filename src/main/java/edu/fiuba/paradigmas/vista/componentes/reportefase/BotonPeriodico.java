package edu.fiuba.paradigmas.vista.componentes.reportefase;

import javafx.scene.control.Button;

public class BotonPeriodico extends Button {

    public BotonPeriodico(String texto) {
        super(texto);
        this.setPrefWidth(250);
        this.setStyle("-fx-background-color: #2c2c2c; -fx-text-fill: #e3d5c1; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 5; -fx-padding: 15; -fx-cursor: hand;");
        this.setFocusTraversable(false);
    }
}