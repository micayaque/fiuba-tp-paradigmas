package edu.fiuba.paradigmas.vista.componentes.resumen;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class ListaEventosHistorial extends ScrollPane {

    public ListaEventosHistorial(List<String> eventos) {
        this.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        this.setFitToWidth(true);
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        this.setPrefHeight(400);

        VBox contenedorEventos = new VBox(10);
        contenedorEventos.setAlignment(Pos.TOP_CENTER);
        contenedorEventos.setPadding(new Insets(10));

        if (eventos.isEmpty()) {
            Label lblVacio = new Label("No hay registros en esta partida.");
            lblVacio.setStyle("-fx-text-fill: #718096; -fx-font-style: italic;");
            contenedorEventos.getChildren().add(lblVacio);
        } else {
            for (int i = 0; i < eventos.size(); i++) {
                contenedorEventos.getChildren().add(crearTarjetaEvento(i + 1, eventos.get(i)));
            }
        }
        this.setContent(contenedorEventos);
    }

    private HBox crearTarjetaEvento(int numero, String textoEvento) {
        HBox tarjeta = new HBox(15);
        tarjeta.setAlignment(Pos.CENTER_LEFT);
        tarjeta.setStyle("-fx-background-color: #12213d; -fx-background-radius: 10; -fx-padding: 15;");
        tarjeta.setMaxWidth(400);

        Label lblNumero = new Label(String.valueOf(numero));
        lblNumero.setStyle("-fx-background-color: #4a6274; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15; -fx-min-width: 30; -fx-min-height: 30; -fx-alignment: center;");

        Label lblTexto = new Label(textoEvento);
        lblTexto.setStyle("-fx-text-fill: #e2e8f0;");
        lblTexto.setWrapText(true);

        tarjeta.getChildren().addAll(lblNumero, lblTexto);
        return tarjeta;
    }
}