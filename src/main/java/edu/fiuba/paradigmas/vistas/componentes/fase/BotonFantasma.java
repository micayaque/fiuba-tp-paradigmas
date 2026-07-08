package edu.fiuba.paradigmas.vistas.componentes.fase;

import javafx.scene.control.Button;

public class BotonFantasma extends Button {

    public BotonFantasma(String texto) {
        super(texto);
        this.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-size: 16px; " +
                        "-fx-border-color: #cbd5e1; " +
                        "-fx-border-radius: 15; " +
                        "-fx-border-width: 1.5; " +
                        "-fx-padding: 12 30; " +
                        "-fx-cursor: hand;"
        );
    }
}