package edu.fiuba.paradigmas.vista.componentes;

import javafx.scene.control.Label;

public class EtiquetaAdvertencia extends Label {

    public EtiquetaAdvertencia() {
        super("");

        this.setStyle("-fx-text-fill: #ff416c; -fx-font-size: 14px; -fx-font-weight: bold; -fx-alignment: center; -fx-text-alignment: center;");
        this.setWrapText(true);
    }

    public void mostrarMensaje(String mensaje) {
        this.setText(mensaje);
    }

    public void limpiar() {
        this.setText("");
    }
}