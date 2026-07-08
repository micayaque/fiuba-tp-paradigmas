package edu.fiuba.paradigmas.vistas.componentes.fase;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class IconoDiaVista extends StackPane {

    public IconoDiaVista() {
        this.setPadding(new Insets(0, 0, 20, 0));

        Circle lunaFondo = new Circle(25);
        lunaFondo.setFill(Color.web("#ffffff", 0.15));
        lunaFondo.setTranslateX(75);
        lunaFondo.setTranslateY(-45);

        Circle haloExterior = new Circle(95);
        haloExterior.setFill(Color.web("#ffc300", 0.15));

        Circle haloMedio = new Circle(70);
        haloMedio.setFill(Color.web("#ffc300", 0.35));

        Circle solCentro = new Circle(50);
        solCentro.setFill(Color.web("#ffc300"));

        Label iconoCentro = new Label("☀");
        iconoCentro.setStyle("-fx-text-fill: white; -fx-font-size: 50px;");

        this.getChildren().addAll(lunaFondo, haloExterior, haloMedio, solCentro, iconoCentro);
    }
}