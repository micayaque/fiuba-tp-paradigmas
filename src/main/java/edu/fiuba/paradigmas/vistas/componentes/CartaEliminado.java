package edu.fiuba.paradigmas.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.File;

public class CartaEliminado extends VBox {

    public CartaEliminado(String nombreJugador, String nombreRol, String archivoImagen) {
        this.setSpacing(8);
        this.setAlignment(Pos.CENTER);

        this.setOpacity(0.4);
        ColorAdjust escalaGrises = new ColorAdjust();
        escalaGrises.setSaturation(-0.85);
        this.setEffect(escalaGrises);

        Label lblNombre = new Label(nombreJugador);
        lblNombre.setFont(new Font("Georgia", 16));
        lblNombre.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        String rutaRelativa = "src/main/recursos/imagenes/cartas/" + archivoImagen;
        File archivoReal = new File(rutaRelativa);

        ImageView vistaImagen = new ImageView(new Image(archivoReal.toURI().toString()));
        vistaImagen.setFitHeight(110);
        vistaImagen.setPreserveRatio(true);

        this.getChildren().addAll(vistaImagen, lblNombre);
    }
}