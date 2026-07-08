package edu.fiuba.paradigmas.vista.componentes.configuracion;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class FilaContadorRol extends HBox {

    private int cantidad;
    private final Label lblCantidad;
    private final Button btnMenos;
    private final Button btnMas;

    public FilaContadorRol(String nombreRol, boolean esEspecial) {
        this.cantidad = 0;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setStyle("-fx-background-color: #12213d; -fx-background-radius: 10; -fx-padding: 10 20;");

        Label lblNombre = new Label(nombreRol);
        lblNombre.setStyle("-fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
        if (esEspecial) {
            lblNombre.setStyle("-fx-text-fill: #f1c40f; -fx-font-size: 16px; -fx-font-weight: bold;");
        }

        Region espaciador = new Region();
        HBox.setHgrow(espaciador, Priority.ALWAYS);

        String estiloBoton = "-fx-background-color: #1a2c4c; -fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold; -fx-cursor: hand; -fx-background-radius: 5;";

        this.btnMenos = new Button("-");
        this.btnMenos.setStyle(estiloBoton);
        this.btnMenos.setPrefSize(35, 35);

        this.lblCantidad = new Label("0");
        this.lblCantidad.setStyle("-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold;");
        this.lblCantidad.setPrefWidth(30);
        this.lblCantidad.setAlignment(Pos.CENTER);

        this.btnMas = new Button("+");
        this.btnMas.setStyle(estiloBoton);
        this.btnMas.setPrefSize(35, 35);

        this.getChildren().addAll(lblNombre, espaciador, this.btnMenos, this.lblCantidad, this.btnMas);
    }

    public int getCantidad() { return this.cantidad; }

    public void configurarAcciones(Runnable alRestar, Runnable alSumar) {
        this.btnMenos.setOnAction(e -> {
            this.cantidad--;
            this.lblCantidad.setText(String.valueOf(this.cantidad));
            alRestar.run();
        });

        this.btnMas.setOnAction(e -> {
            this.cantidad++;
            this.lblCantidad.setText(String.valueOf(this.cantidad));
            alSumar.run();
        });
    }

    public void deshabilitarSuma(boolean deshabilitar) {
        this.btnMas.setDisable(deshabilitar);
    }

    public void deshabilitarResta(boolean deshabilitar) {
        this.btnMenos.setDisable(deshabilitar);
    }
}