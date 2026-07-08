package edu.fiuba.paradigmas.vista.componentes.reportefase;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CabeceraPeriodico extends VBox {

    public CabeceraPeriodico(String edicion) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        this.setOnMousePressed(Event::consume);

        Line lineaSup = new Line(0, 0, 300, 0);
        lineaSup.setStyle("-fx-stroke: #2c2c2c; -fx-stroke-width: 3;");

        HBox tituloCaja = new HBox(10);
        tituloCaja.setAlignment(Pos.CENTER);

        Label lblTitulo = new Label("Crónicas de \n la Aldea");
        lblTitulo.setFont(Font.font("Serif", FontWeight.BOLD, 32));
        lblTitulo.setStyle("-fx-text-fill: #1a1a1a; -fx-text-alignment: center;");

        tituloCaja.getChildren().addAll(new Label("📰"), lblTitulo, new Label("📰"));

        Line lineaInf = new Line(0, 0, 300, 0);
        lineaInf.setStyle("-fx-stroke: #2c2c2c; -fx-stroke-width: 1;");

        Label lblEdicion = new Label(edicion + " • Reporte Especial");
        lblEdicion.setFont(Font.font("Serif", 14));
        lblEdicion.setStyle("-fx-font-style: italic; -fx-text-fill: #4a4a4a;");

        this.getChildren().addAll(lineaSup, tituloCaja, lineaInf, lblEdicion);
    }
}