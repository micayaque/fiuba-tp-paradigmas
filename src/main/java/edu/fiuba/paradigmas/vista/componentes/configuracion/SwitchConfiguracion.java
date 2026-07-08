package edu.fiuba.paradigmas.vista.componentes.configuracion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;

public class SwitchConfiguracion extends HBox {

    private final ToggleButton toggleButton;

    public SwitchConfiguracion(String textoEtiqueta) {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15);
        this.setPadding(new Insets(15, 0, 0, 0));

        Label lblTexto = new Label(textoEtiqueta);
        lblTexto.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        this.toggleButton = new ToggleButton("OFF");
        this.toggleButton.setSelected(false);
        this.toggleButton.setPrefSize(65, 30);

        String estiloOn = "-fx-background-color: #10b981; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 15; -fx-cursor: hand;";
        String estiloOff = "-fx-background-color: #4a5568; -fx-text-fill: #a0aec0; -fx-font-weight: bold; -fx-background-radius: 15; -fx-cursor: hand;";

        this.toggleButton.setStyle(estiloOff);

        this.toggleButton.selectedProperty().addListener((observable, viejoValor, nuevoValor) -> {
            if (nuevoValor) {
                this.toggleButton.setText("ON");
                this.toggleButton.setStyle(estiloOn);
            } else {
                this.toggleButton.setText("OFF");
                this.toggleButton.setStyle(estiloOff);
            }
        });
        this.getChildren().addAll(lblTexto, this.toggleButton);
    }

    public boolean isActivado() {
        return this.toggleButton.isSelected();
    }
}