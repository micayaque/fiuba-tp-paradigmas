package edu.fiuba.paradigmas.vistas.componentes.configuracion;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.util.function.Consumer;

public class ListaSeleccionCantidadJugadores extends ScrollPane {

    private final VBox contenedorBotones;

    public ListaSeleccionCantidadJugadores() {
        this.setStyle("-fx-background: #0b1426; -fx-border-color: transparent;");
        this.setFitToWidth(true);
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);

        this.contenedorBotones = new VBox();
        this.contenedorBotones.setAlignment(Pos.TOP_CENTER);
        this.contenedorBotones.setStyle("-fx-background-color: #0b1426;");

        this.setContent(this.contenedorBotones);
    }

    public void popularOpciones(int min, int max, Consumer<Integer> accionAlSeleccionar) {
        this.contenedorBotones.getChildren().clear();

        for (int i = min; i <= max; i++) {
            final int cantidad = i;
            Button btnOpcion = new Button(String.valueOf(cantidad));
            btnOpcion.setMaxWidth(Double.MAX_VALUE);
            btnOpcion.setPrefHeight(60);

            String estiloNormal = "-fx-background-color: #12213d; -fx-text-fill: white; -fx-font-size: 18px; -fx-border-color: #1a2c4c; -fx-border-width: 0 0 2 0; -fx-cursor: hand;";
            String estiloHover = "-fx-background-color: #1a2c4c; -fx-text-fill: white; -fx-font-size: 18px; -fx-border-color: #2c4a7c; -fx-border-width: 0 0 2 0; -fx-cursor: hand;";

            btnOpcion.setStyle(estiloNormal);
            btnOpcion.setOnMouseEntered(e -> btnOpcion.setStyle(estiloHover));
            btnOpcion.setOnMouseExited(e -> btnOpcion.setStyle(estiloNormal));

            btnOpcion.setOnAction(e -> accionAlSeleccionar.accept(cantidad));

            this.contenedorBotones.getChildren().add(btnOpcion);
        }
    }
}