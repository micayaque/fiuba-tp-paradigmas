package edu.fiuba.paradigmas.vista.componentes.reportefase;

import javafx.event.Event;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SeccionNoticias extends VBox {

    public SeccionNoticias(String titulo, String texto, boolean esUrgente) {
        this.setSpacing(5);

        Label lblTit = new Label(titulo);
        lblTit.setFont(Font.font("Serif", FontWeight.BOLD, 14));
        lblTit.setStyle("-fx-text-fill: " + (esUrgente ? "#8b0000" : "#1a1a1a") + ";");

        Line separador = new Line(0, 0, 280, 0);
        separador.setStyle("-fx-stroke: #dcdcdc;");

        Label lblTex = new Label(texto);
        lblTex.setFont(Font.font("Serif", 14));
        lblTex.setStyle("-fx-text-fill: #4a4a4a;");
        lblTex.setWrapText(true);

        this.getChildren().addAll(lblTit, separador, lblTex);
        this.setOnMousePressed(Event::consume);
    }
}