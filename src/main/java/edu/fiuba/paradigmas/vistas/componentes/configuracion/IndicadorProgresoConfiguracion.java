package edu.fiuba.paradigmas.vistas.componentes.configuracion;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class IndicadorProgresoConfiguracion extends HBox {

    public IndicadorProgresoConfiguracion(int pasoActivo) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(0);

        VBox paso1 = crearPaso("Jugadores", pasoActivo == 1);
        Region linea1 = crearLineaConectora();

        VBox paso2 = crearPaso("Nombres", pasoActivo == 2);
        Region linea2 = crearLineaConectora();

        VBox paso3 = crearPaso("Roles", pasoActivo == 3);

        this.getChildren().addAll(paso1, linea1, paso2, linea2, paso3);
    }

    private Region crearLineaConectora() {
        Region linea = new Region();
        linea.setPrefSize(50, 2);
        linea.setMaxSize(50, 2);
        linea.setMinSize(50, 2);
        linea.setStyle("-fx-background-color: #2c4a7c; -fx-translate-y: -10;");
        return linea;
    }

    private VBox crearPaso(String texto, boolean activo) {
        VBox contenedor = new VBox(5);
        contenedor.setAlignment(Pos.CENTER);

        Circle circulo = new Circle(15);
        if (activo) {
            circulo.setFill(Color.web("#e2e8f0"));
            circulo.setStyle("-fx-effect: dropshadow(gaussian, #ffffff, 15, 0.3, 0, 0);");
        } else {
            circulo.setFill(Color.TRANSPARENT);
            circulo.setStroke(Color.web("#2c4a7c"));
            circulo.setStrokeWidth(2);
        }

        Label etiqueta = new Label(texto);
        etiqueta.setStyle(activo ? "-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 12px;" : "-fx-text-fill: #718096;");

        contenedor.getChildren().addAll(circulo, etiqueta);
        return contenedor;
    }
}