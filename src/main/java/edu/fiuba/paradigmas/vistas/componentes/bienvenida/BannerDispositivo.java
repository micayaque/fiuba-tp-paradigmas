package edu.fiuba.paradigmas.vistas.componentes.bienvenida;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BannerDispositivo extends HBox {

    public BannerDispositivo() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPrefWidth(280);
        this.setMaxWidth(280);
        this.setStyle("-fx-background-color: #0b172a; -fx-background-radius: 5; -fx-padding: 10;");

        Rectangle iconoCelular = new Rectangle(10, 16, Color.TRANSPARENT);
        iconoCelular.setStroke(Color.web("#4299e1"));
        iconoCelular.setStrokeWidth(2);
        iconoCelular.setArcWidth(4);
        iconoCelular.setArcHeight(4);

        Label textoBanner = new Label("Pasa el dispositivo para revelar los roles.");
        textoBanner.setStyle("-fx-text-fill: #718096; -fx-font-size: 11px;");

        this.getChildren().addAll(iconoCelular, textoBanner);
    }
}