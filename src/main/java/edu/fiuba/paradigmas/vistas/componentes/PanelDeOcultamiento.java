package edu.fiuba.paradigmas.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PanelDeOcultamiento extends VBox {
    private final Label lblNombreJugador;
    private final Button btnVerCarta;

    public PanelDeOcultamiento() {
        this.setSpacing(30);
        this.setAlignment(Pos.CENTER);

        this.lblNombreJugador = new Label("Pasale el dispositivo a...");
        this.lblNombreJugador.setFont(new Font("Georgia", 40));
        this.lblNombreJugador.setStyle("-fx-text-fill: #f5f1e8; -fx-font-weight: bold;");

        Label lblAdvertencia = new Label("Asegurate de que nadie más esté mirando la pantalla");
        lblAdvertencia.setFont(new Font("Georgia", 18));
        lblAdvertencia.setStyle("-fx-text-fill: #d6cfc2;");

        this.btnVerCarta = new Button("Ver carta");
        this.btnVerCarta.setStyle("-fx-background-color: linear-gradient(#931621, #4a0808); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 14 30; -fx-background-radius: 8; -fx-font-size: 16px;");
        this.btnVerCarta.setCursor(Cursor.HAND);

        this.btnVerCarta.setOnMouseEntered(e -> this.btnVerCarta.setStyle("-fx-background-color: linear-gradient(#b01927, #5c0a0a); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 14 30; -fx-background-radius: 8; -fx-font-size: 16px;"));
        this.btnVerCarta.setOnMouseExited(e -> this.btnVerCarta.setStyle("-fx-background-color: linear-gradient(#931621, #4a0808); -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 14 30; -fx-background-radius: 8; -fx-font-size: 16px;"));

        this.getChildren().addAll(lblNombreJugador, lblAdvertencia, btnVerCarta);
    }

    public void setNombreJugador(String nombre) {
        this.lblNombreJugador.setText("Pasale el dispositivo a: " + nombre);
    }

    public void alPresionarVerCarta(Runnable accion) {
        this.btnVerCarta.setOnAction(e -> accion.run());
    }

    public void setTextoBoton(String texto) {
        this.btnVerCarta.setText(texto);
    }
}