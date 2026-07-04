package edu.fiuba.paradigmas.vistas.componentes;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.io.File;

public class PanelDeRevelacion extends VBox {

    private final Label lblDescripcionRol;
    private final Button btnOcultarCarta;
    private final ImageView imagenCarta;

    public PanelDeRevelacion() {
        this.setSpacing(25);
        this.setAlignment(Pos.CENTER);

        Label lblTuRolEs = new Label("Tu rol es:");
        lblTuRolEs.setFont(new Font("Georgia", 24));
        lblTuRolEs.setStyle("-fx-text-fill: #d6cfc2;");

        this.imagenCarta = new ImageView();
        this.imagenCarta.setFitHeight(300);
        this.imagenCarta.setPreserveRatio(true);

        this.lblDescripcionRol = new Label();
        this.lblDescripcionRol.setFont(new Font("Georgia", 18));
        this.lblDescripcionRol.setStyle("-fx-text-fill: #a8a8a8;");
        this.lblDescripcionRol.setTextAlignment(TextAlignment.CENTER);
        this.lblDescripcionRol.setWrapText(true);
        this.lblDescripcionRol.setMaxWidth(350);

        this.btnOcultarCarta = new Button("Ocultar carta");
        this.btnOcultarCarta.setStyle("-fx-background-color: #2a3b4c; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 14 30; -fx-background-radius: 8; -fx-font-size: 16px;");
        this.btnOcultarCarta.setCursor(Cursor.HAND);

        this.getChildren().addAll(lblTuRolEs, this.imagenCarta, this.lblDescripcionRol, this.btnOcultarCarta);
    }

    public void setRol(String archivoImagen, String descripcion) {
        this.lblDescripcionRol.setText(descripcion);

            String rutaRelativa = "src/main/recursos/imagenes/cartas/" + archivoImagen;
            File archivoReal = new File(rutaRelativa);
            Image imagen = new Image(archivoReal.toURI().toString());
            this.imagenCarta.setImage(imagen);
    }

    public void alPresionarOcultarCarta(Runnable accion) {
        this.btnOcultarCarta.setOnAction(e -> accion.run());
    }
}