package edu.fiuba.paradigmas.vistas.componentes;

import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class BotonPersonalizado extends Button {
    public BotonPersonalizado(String texto) {
        super(texto);

        this.setStyle("-fx-background-color: linear-gradient(#2c3e50, #1f2d3a); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 18; -fx-background-radius: 8; -fx-cursor: hand;");
        this.setCursor(Cursor.HAND);
    }
}
