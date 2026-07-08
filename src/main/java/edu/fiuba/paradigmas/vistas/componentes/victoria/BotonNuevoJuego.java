package edu.fiuba.paradigmas.vistas.componentes.victoria;

import javafx.scene.control.Button;

public class BotonNuevoJuego extends Button {

    public BotonNuevoJuego(String texto) {
        super(texto);
        this.setPrefWidth(150);
        this.setStyle("-fx-background-color: transparent; " +
                "-fx-border-color: #4a6274; " +
                "-fx-border-radius: 10; " +
                "-fx-border-width: 2; " +
                "-fx-text-fill: #8fa0b5; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 8; " +
                "-fx-cursor: hand;");
    }
}