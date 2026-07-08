package edu.fiuba.paradigmas.vista.componentes;

import javafx.scene.control.Button;

public class BotonContinuar extends Button {

    private static final String ESTILO_INACTIVO = "-fx-background-color: #4a5d85; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 20; -fx-padding: 15; -fx-cursor: hand;";
    private static final String ESTILO_ACTIVO = "-fx-background-color: #00b894; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px; -fx-background-radius: 20; -fx-padding: 15; -fx-cursor: hand;";

    public BotonContinuar(String texto) {
        super(texto);
        this.setPrefWidth(250);
        this.setStyle(ESTILO_INACTIVO);
        this.setDisable(true);
    }

    public void setListoParaAvanzar(boolean estaListo) {
        this.setDisable(!estaListo);
        if (estaListo) {
            this.setStyle(ESTILO_ACTIVO);
        } else {
            this.setStyle(ESTILO_INACTIVO);
        }
    }
}