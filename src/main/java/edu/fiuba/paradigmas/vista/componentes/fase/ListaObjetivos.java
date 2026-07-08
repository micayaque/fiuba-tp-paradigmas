package edu.fiuba.paradigmas.vista.componentes.fase;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Consumer;

public class ListaObjetivos extends ScrollPane {

    private final VBox contenedorBotones;
    private final List<String> objetivosDisponibles;

    public ListaObjetivos(List<String> objetivosDisponibles) {
        this.objetivosDisponibles = objetivosDisponibles;

        this.contenedorBotones = new VBox(10);
        this.contenedorBotones.setAlignment(Pos.CENTER);

        this.setContent(this.contenedorBotones);
        this.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-focus-color: transparent; -fx-faint-focus-color: transparent;");
        this.setFitToWidth(true);
        this.setPrefHeight(250);
    }

    public void configurarAcciones(boolean permitirOmitir, Consumer<String> alSeleccionar) {
        this.contenedorBotones.getChildren().clear();

        if (permitirOmitir) {
            this.contenedorBotones.getChildren().add(crearBotonObjetivo("Abstenerse", alSeleccionar));
        }

        for (String objetivo : this.objetivosDisponibles) {
            this.contenedorBotones.getChildren().add(crearBotonObjetivo(objetivo, alSeleccionar));
        }
    }

    private Button crearBotonObjetivo(String nombre, Consumer<String> alSeleccionar) {
        Button btn = new Button();
        btn.setMaxWidth(Double.MAX_VALUE);
        btn.setStyle("-fx-background-color: #1a2c4c; -fx-text-fill: white; -fx-font-size: 16px; -fx-background-radius: 10; -fx-padding: 15; -fx-alignment: center-left; -fx-cursor: hand;");

        HBox contenido = new HBox(15);
        contenido.setAlignment(Pos.CENTER_LEFT);
        Label lblAvatar = new Label("👤");
        lblAvatar.setStyle("-fx-text-fill: #a0aec0;");
        Label lblNombre = new Label(nombre);
        lblNombre.setStyle("-fx-text-fill: white;");

        contenido.getChildren().addAll(lblAvatar, lblNombre);
        btn.setGraphic(contenido);

        btn.setOnAction(e -> alSeleccionar.accept(nombre));

        return btn;
    }
}