package edu.fiuba.paradigmas.vistas.componentes.configuracion;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BarraProgresoNombres extends HBox {

    private final Rectangle barraLlena;
    private final Label lblTexto;
    private final int totalJugadores;
    private final double anchoMaximo = 250;

    public BarraProgresoNombres(int totalJugadores) {
        this.totalJugadores = totalJugadores;
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setSpacing(15);
        this.setPrefWidth(350);
        this.setMaxWidth(350);
        this.setStyle("-fx-background-color: #12213d; -fx-background-radius: 20; -fx-padding: 5 15 5 5;");

        StackPane contenedorBarra = new StackPane();
        contenedorBarra.setAlignment(Pos.CENTER_LEFT);

        Rectangle barraVacia = new Rectangle(this.anchoMaximo, 20, Color.TRANSPARENT);

        this.barraLlena = new Rectangle(0, 20);
        this.barraLlena.setStyle("-fx-fill: linear-gradient(to right, #ff416c, #ff4b2b);");
        this.barraLlena.setArcWidth(20);
        this.barraLlena.setArcHeight(20);

        contenedorBarra.getChildren().addAll(barraVacia, this.barraLlena);

        this.lblTexto = new Label("0 / " + totalJugadores);
        this.lblTexto.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;");

        HBox.setHgrow(contenedorBarra, javafx.scene.layout.Priority.ALWAYS);

        this.getChildren().addAll(contenedorBarra, this.lblTexto);
        this.actualizarProgreso(0);
    }

    public void actualizarProgreso(int completados) {
        double nuevoAncho = (completados * this.anchoMaximo) / this.totalJugadores;
        this.barraLlena.setWidth(nuevoAncho);
        this.lblTexto.setText(completados + " / " + this.totalJugadores);
    }
}