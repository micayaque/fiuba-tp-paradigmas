package edu.fiuba.paradigmas.vistas.componentes.reportefase;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TarjetaCita extends VBox {

    public TarjetaCita(String cita, String autor) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(5);
        this.setStyle("-fx-background-color: #f7f4ec; -fx-border-color: #8b0000; -fx-border-width: 0 0 0 3; -fx-padding: 15;");
        this.setMaxWidth(320);
        this.setOnMousePressed(Event::consume);

        Label lblCita = new Label("\"" + cita + "\"");
        lblCita.setFont(Font.font("Serif", 14));
        lblCita.setStyle("-fx-font-style: italic; -fx-text-fill: #4a4a4a; -fx-text-alignment: center;");
        lblCita.setWrapText(true);

        Label lblAutor = new Label("— " + autor);
        lblAutor.setFont(Font.font("Serif", 12));
        lblAutor.setStyle("-fx-text-fill: #7a7a7a;");

        this.getChildren().addAll(lblCita, lblAutor);
    }
}