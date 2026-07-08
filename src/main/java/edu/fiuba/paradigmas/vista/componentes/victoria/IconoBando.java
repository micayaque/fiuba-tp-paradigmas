package edu.fiuba.paradigmas.vista.componentes.victoria;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class IconoBando extends VBox {

    public IconoBando(String emoji, String colorFondoHex) {
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: " + colorFondoHex + "; -fx-background-radius: 30;");

        this.setMinSize(130, 130);
        this.setMaxSize(130, 130);

        Label lblIcono = new Label(emoji);
        lblIcono.setStyle("-fx-font-size: 65px;");

        lblIcono.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

        this.getChildren().add(lblIcono);
    }
}