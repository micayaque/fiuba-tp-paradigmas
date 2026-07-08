package edu.fiuba.paradigmas.vista.componentes.bienvenida;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LogoMafia extends VBox {

    public LogoMafia() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);

        StackPane logoLuna = new StackPane();
        Circle circuloExterior = new Circle(80);
        circuloExterior.setFill(Color.TRANSPARENT);
        circuloExterior.setStroke(Color.web("#1c2b42"));
        circuloExterior.setStrokeWidth(2);

        Circle circuloInterior = new Circle(50);
        circuloInterior.setFill(Color.web("#0e1a2b"));

        Label iconoLuna = new Label("☾");
        iconoLuna.setStyle("-fx-text-fill: #cdd6e5; -fx-font-size: 60px;");
        iconoLuna.setRotate(15);
        logoLuna.getChildren().addAll(circuloExterior, circuloInterior, iconoLuna);

        Label titulo = new Label("M A F I A");
        titulo.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 48));
        titulo.setStyle("-fx-text-fill: white; -fx-effect: dropshadow(gaussian, #4a90e2, 10, 0.5, 0, 0);");

        HBox divisor = new HBox(10);
        divisor.setAlignment(Pos.CENTER);
        Region lineaIzq = new Region(); lineaIzq.setPrefSize(80, 2); lineaIzq.setStyle("-fx-background-color: #1c2b42;");
        Rectangle diamante = new Rectangle(8, 8, Color.web("#4a90e2")); diamante.setRotate(45);
        Region lineaDer = new Region(); lineaDer.setPrefSize(80, 2); lineaDer.setStyle("-fx-background-color: #1c2b42;");
        divisor.getChildren().addAll(lineaIzq, diamante, lineaDer);

        this.getChildren().addAll(logoLuna, titulo, divisor);
    }
}