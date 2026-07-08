package edu.fiuba.paradigmas.vistas.componentes.configuracion;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EncabezadoConfiguracion extends BorderPane {

    private final Button btnVolver;

    public EncabezadoConfiguracion(String tituloFormulario) {
        this.setStyle("-fx-background-color: transparent; -fx-padding: 10 20;");

        this.btnVolver = new Button("<");
        this.btnVolver.setStyle("-fx-background-color: #1a2c4c; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold; -fx-background-radius: 10; -fx-cursor: hand;");
        this.btnVolver.setPrefSize(40, 40);
        this.setLeft(this.btnVolver);

        Label lblTitulo = new Label(tituloFormulario);
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        lblTitulo.setStyle("-fx-text-fill: white;");
        BorderPane.setAlignment(lblTitulo, Pos.CENTER);
        this.setCenter(lblTitulo);
    }

    public void configurarBotonVolver(Runnable accion) {
        this.btnVolver.setOnAction(e -> accion.run());
    }
}