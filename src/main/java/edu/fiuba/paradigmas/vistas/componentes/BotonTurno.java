package edu.fiuba.paradigmas.vistas.componentes;

import javafx.scene.control.Button;

public class BotonTurno extends Button {

    public BotonTurno(String texto, double ancho) {
        super(texto);
        this.setPrefWidth(ancho);
        this.setStyle("-fx-background-color: #4a5d85; " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-background-radius: 10; " +
                "-fx-padding: 10; " +
                "-fx-cursor: hand;");
    }
}