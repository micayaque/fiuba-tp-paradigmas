package edu.fiuba.paradigmas.vistas.componentes;

import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class BotonPersonalizado extends Button {
    public BotonPersonalizado(String texto) {
        super(texto);

        this.setStyle("-fx-background-color: #2c3e50; -fx-text-fill: white; -fx-font-weight: bold;");
        this.setCursor(Cursor.HAND);
    }
}
