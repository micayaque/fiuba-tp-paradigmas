package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.vistas.componentes.BotonPersonalizado;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class BienvenidaVista extends VBox {
    private final Label titulo;
    private final Label subtitulo;
    private final BotonPersonalizado continuar;

    public BienvenidaVista() {
        this.setSpacing(22);
        this.setPadding(new Insets(26));
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: linear-gradient(to bottom right, #050505, #171717); -fx-min-width: 780px; -fx-min-height: 620px;");

        StackPane cartaCiudadano = crearCartaConImagen("ciudadanos.png", -4);
        StackPane cartaMafia = crearCartaConImagen("mafia.png", 4);
        HBox escenario = new HBox(18, cartaCiudadano, cartaMafia);

        escenario.setAlignment(Pos.CENTER);
        this.titulo = new Label("Bienvenido al juego Mafia");
        this.titulo.setFont(new Font("Georgia", 31));
        this.titulo.setStyle("-fx-text-fill: #f5f1e8; -fx-font-weight: bold;");

        this.subtitulo = new Label("Descubrí quién domina la noche.");
        this.subtitulo.setFont(new Font("Georgia", 15));
        this.subtitulo.setStyle("-fx-text-fill: #d6cfc2;");
        this.subtitulo.setWrapText(true);
        this.subtitulo.setMaxWidth(420);
        this.subtitulo.setAlignment(Pos.CENTER);

        this.continuar = new BotonPersonalizado("Configurar partida");
        this.continuar.setMaxWidth(280);
        this.continuar.setStyle("-fx-background-color: linear-gradient(#c21f1f, #8f1010); -fx-text-fill: #f5f1e8; -fx-font-weight: bold; -fx-padding: 12 22; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: rgba(245,241,232,0.25);");

        VBox contenido = new VBox(22, escenario, this.titulo, this.subtitulo, this.continuar);
        contenido.setPadding(new Insets(26));
        contenido.setAlignment(Pos.CENTER);

        StackPane lienzo = new StackPane(contenido);
        this.getChildren().add(lienzo);
    }

    public void alPresionarContinuar(Runnable accion) {
        this.continuar.setOnAction(e -> accion.run());
    }

    private StackPane crearCartaConImagen(String nombreArchivo, double rotacion) {
        Rectangle marco = new Rectangle(240, 340);
        marco.setArcWidth(24);
        marco.setArcHeight(24);
        marco.setStyle("-fx-fill: #070707; -fx-stroke: #d4af37; -fx-stroke-width: 3;");

        String rutaRelativa = "src/main/recursos/imagenes/cartas/" + nombreArchivo;
        java.io.File archivoReal = new java.io.File(rutaRelativa);

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