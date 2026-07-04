package edu.fiuba.paradigmas.vistas.componentes;

import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class BotonRepartirCartas extends Button {

    public BotonRepartirCartas() {
        super("Repartir cartas");

        this.setStyle("-fx-background-color: linear-gradient(#931621, #4a0808); " +
                "-fx-text-fill: white; " +
                "-fx-font-weight: bold; " +
                "-fx-padding: 12; " +
                "-fx-background-radius: 8;");

        this.setCursor(Cursor.HAND);

        this.setOnMouseEntered(e -> this.setStyle("-fx-background-color: linear-gradient(#b01927, #5c0a0a); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12; -fx-background-radius: 8;"));
        this.setOnMouseExited(e -> this.setStyle("-fx-background-color: linear-gradient(#931621, #4a0808); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 12; -fx-background-radius: 8;"));
    }
}