package edu.fiuba.paradigmas.vista.componentes.victoria;

import javafx.scene.control.Button;

public class BotonResumen extends Button {

    public BotonResumen(String texto) {
        super(texto);
        this.setPrefWidth(250);
        this.setStyle("-fx-background-color: linear-gradient(to right, #f1c40f, #e67e22); " +
                "-fx-text-fill: #3e2723; " +
                "-fx-font-weight: bold; " +
                "-fx-font-size: 16px; " +
                "-fx-background-radius: 20; " +
                "-fx-padding: 15; " +
                "-fx-cursor: hand;");
    }
}