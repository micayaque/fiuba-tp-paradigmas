package edu.fiuba.paradigmas.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TituloIngresoNombres extends VBox {

    public TituloIngresoNombres(String titulo, String subtitulo) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);

        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        lblTitulo.setStyle("-fx-text-fill: white;");

        Label lblSubtitulo = new Label(subtitulo);
        lblSubtitulo.setStyle("-fx-text-fill: #a0aec0; -fx-font-size: 12px;");

        this.getChildren().addAll(lblTitulo, lblSubtitulo);
    }
}