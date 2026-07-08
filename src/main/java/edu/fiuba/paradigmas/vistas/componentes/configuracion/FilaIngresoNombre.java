package edu.fiuba.paradigmas.vistas.componentes.configuracion;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class FilaIngresoNombre extends HBox {

    private final TextField campoTexto;
    private final Label iconoCheck;

    private final String colorInactivo = "#12213d";
    private final String bordeNormal = "transparent";
    private final String bordeValido = "#00b894";

    public FilaIngresoNombre(int numeroSorteo, Runnable accionAlCambiarTexto) {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setSpacing(10);
        this.setPadding(new Insets(5, 15, 5, 0));
        this.setStyle("-fx-background-color: " + colorInactivo + "; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-color: " + bordeNormal + ";");

        Label lblNumero = new Label(String.valueOf(numeroSorteo));
        lblNumero.setPrefWidth(40);
        lblNumero.setAlignment(Pos.CENTER);
        lblNumero.setStyle("-fx-text-fill: #00b894; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-color: #0d1b2a; -fx-background-radius: 8 0 0 8;");
        lblNumero.setMaxHeight(Double.MAX_VALUE);

        this.campoTexto = new TextField();
        this.campoTexto.setPromptText("Jugador " + numeroSorteo);
        this.campoTexto.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-prompt-text-fill: #4a5568; -fx-font-size: 16px;");
        HBox.setHgrow(this.campoTexto, Priority.ALWAYS);

        this.iconoCheck = new Label("✔");
        this.iconoCheck.setStyle("-fx-text-fill: " + bordeValido + "; -fx-font-size: 16px; -fx-background-color: #0d1b2a; -fx-background-radius: 10; -fx-padding: 2 6;");
        this.iconoCheck.setVisible(false);

        this.campoTexto.focusedProperty().addListener((obs, viejo, tieneFoco) -> {
            this.actualizarEstiloVisua(tieneFoco);
        });

        this.campoTexto.textProperty().addListener((obs, viejoTexto, nuevoTexto) -> {
            boolean tieneTexto = !nuevoTexto.trim().isEmpty();
            this.iconoCheck.setVisible(tieneTexto);
            this.actualizarEstiloVisua(this.campoTexto.isFocused());

            accionAlCambiarTexto.run();
        });

        this.getChildren().addAll(lblNumero, this.campoTexto, this.iconoCheck);
    }

    private void actualizarEstiloVisua(boolean tieneFoco) {
        boolean esValido = !this.campoTexto.getText().trim().isEmpty();
        String colorBorde = bordeNormal;

        if (tieneFoco) {
            colorBorde = "#f1c40f";
        } else if (esValido) {
            colorBorde = bordeValido;
        }

        String colorActivo = "#0d1b2a";
        this.setStyle("-fx-background-color: " + (tieneFoco ? colorActivo : colorInactivo) +
                "; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-width: 2; -fx-border-color: " + colorBorde + ";");
    }

    public String getTexto() {
        return this.campoTexto.getText().trim();
    }

    public boolean estaCompleto() {
        return !this.getTexto().isEmpty();
    }

    public String getPlaceholder() {
        return this.campoTexto.getPromptText();
    }
}