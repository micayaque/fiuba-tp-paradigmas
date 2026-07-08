package edu.fiuba.paradigmas.vista.componentes.fase;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EncabezadoFase extends VBox {

    public EncabezadoFase(String icono, String titulo) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);

        Label lblIcono = new Label(icono);
        lblIcono.setStyle("-fx-font-size: 40px;");

        Label lblTitulo = new Label(titulo);
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        lblTitulo.setStyle("-fx-text-fill: white;");

        this.getChildren().addAll(lblIcono, lblTitulo);
    }
}