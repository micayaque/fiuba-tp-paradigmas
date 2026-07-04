package edu.fiuba.paradigmas.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import java.io.File;

public class PanelCartasDecorativas extends HBox {

    public PanelCartasDecorativas() {
        super(18);
        this.setAlignment(Pos.CENTER);

        StackPane cartaCiudadano = crearCartaConImagen("ciudadanos.png", -4);
        StackPane cartaMafia = crearCartaConImagen("mafia.png", 4);

        this.getChildren().addAll(cartaCiudadano, cartaMafia);
    }

    private StackPane crearCartaConImagen(String nombreArchivo, double rotacion) {
        Rectangle marco = new Rectangle(240, 340);
        marco.setArcWidth(24);
        marco.setArcHeight(24);
        marco.setStyle("-fx-fill: #070707; -fx-stroke: #d4af37; -fx-stroke-width: 3;");

        String rutaRelativa = "src/main/recursos/imagenes/cartas/" + nombreArchivo;
        File archivoReal = new File(rutaRelativa);

        Image imagen = new Image(archivoReal.toURI().toString());
        ImageView imagenCarta = new ImageView(imagen);
        imagenCarta.setFitWidth(240);
        imagenCarta.setFitHeight(340);
        imagenCarta.setPreserveRatio(false);
        imagenCarta.setSmooth(true);

        Rectangle recorte = new Rectangle(240, 340);
        recorte.setArcWidth(24);
        recorte.setArcHeight(24);
        imagenCarta.setClip(recorte);

        StackPane carta = new StackPane(marco, imagenCarta);
        carta.setRotate(rotacion);
        carta.setStyle("-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.55), 24, 0.25, 0, 8);");

        return carta;
    }
}