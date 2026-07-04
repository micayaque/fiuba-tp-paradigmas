package edu.fiuba.paradigmas.vistas.componentes;

import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class BotonCartaRol extends ToggleButton {
    private final String identificadorRol;
    private boolean bloqueado = false;
    private String motivoBloqueo = "";

    public BotonCartaRol(String archivoImagen, String identificadorRol) {
        this.identificadorRol = identificadorRol;
        this.setSelected(false);

        String rutaRelativa = "src/main/recursos/imagenes/cartas/" + archivoImagen;
        File archivoReal = new File(rutaRelativa);

        ImageView vistaImagen = new ImageView(new Image(archivoReal.toURI().toString()));
        vistaImagen.setFitWidth(110);
        vistaImagen.setPreserveRatio(true);

        this.setGraphic(vistaImagen);

        actualizarEfectoVisual();
        this.selectedProperty().addListener((obs, viejo, nuevo) -> actualizarEfectoVisual());

        this.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> {
            if (bloqueado && !this.isSelected()) {
                e.consume();
                mostrarAlertaBloqueo();
            }
        });
    }

    public void setBloqueado(boolean bloqueado, String motivo) {
        this.bloqueado = bloqueado;
        this.motivoBloqueo = motivo;
        actualizarEfectoVisual();
    }

    private void actualizarEfectoVisual() {
        String estiloBase = "-fx-background-color: transparent; ";

        if (this.isSelected()) {
            this.setStyle(estiloBase + "-fx-cursor: hand; -fx-opacity: 1.0; -fx-effect: dropshadow(gaussian, #d4af37, 15, 0.4, 0, 0);");
        } else if (bloqueado) {
            this.setStyle(estiloBase + "-fx-cursor: default; -fx-opacity: 0.2; -fx-effect: none;");
        } else {
            this.setStyle(estiloBase + "-fx-cursor: hand; -fx-opacity: 0.5; -fx-effect: none;");
        }
    }

    private void mostrarAlertaBloqueo() {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Carta no disponible");
        alerta.setHeaderText("Límite alcanzado");
        alerta.setContentText(motivoBloqueo);
        alerta.showAndWait();
    }

    public String obtenerIdentificadorRol() {
        return identificadorRol;
    }
}