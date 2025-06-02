package edu.fiuba.algo3.vistas.escenas;

import javafx.animation.ScaleTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.Objects;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import edu.fiuba.algo3.controllers.ControladorPartida;

public class VistaPartida extends Scene{
    private FlowPane root;
    private String nombreJugador;

    public VistaPartida(Stage stage, double width, double height) {
        super(new FlowPane(), width, height);
        this.root = (FlowPane) this.getRoot();

        // Establecer el fondo para el root
        BackgroundImage imagenFondo = new BackgroundImage(
                new Image(Objects.requireNonNull(getClass().getResourceAsStream("/backgroundPartida.png"))),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        );
        Background fondo = new Background(imagenFondo);
        this.root.setBackground(fondo);
        // Crear el texto para agregar el nombre del jugador
        Label textoAgregarJugador = new Label(" INGRESE SU NOMBRE ");
        textoAgregarJugador.setStyle("-fx-text-fill: white;" +  // Verde fosforescente
                "-fx-text-alignment: center;" +
                "-fx-font-weight: bold;");  // Borde de 2px

        Font fontCreepster = Font.loadFont(getClass().getResourceAsStream("/fonts/vt.ttf"), 36);
        if (fontCreepster == null) {
            System.out.println("No se pudo cargar la fuente, usando la fuente predeterminada.");
            fontCreepster = Font.font("Arial", 36);  // Fuente de respaldo
        }
        textoAgregarJugador.setFont(fontCreepster);

        // Crear el campo de texto para el nombre del jugador
        TextField fieldNombreJugador = new TextField();
        fieldNombreJugador.setPrefWidth(300);
        fieldNombreJugador.setStyle("-fx-text-fill: yellow;" +
                "-fx-text-alignment: center;" +
                "-fx-background-color: #404040;");  // Gris más oscuro

        fieldNombreJugador.setFont(fontCreepster);

        // Crear el botón para jugar
        Button botonJugar = new Button("Jugar");

        Image imagenFondoMano = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/manoJugar.jpg")));

        BackgroundImage imagenBoton = new BackgroundImage(imagenFondoMano,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(100, 50, false, false, true, false));

        Background fondoBoton = new Background(imagenBoton);
        botonJugar.setBackground(fondoBoton);

        botonJugar.setPrefWidth(200);
        botonJugar.setPrefHeight(150);

        botonJugar.setStyle("-fx-text-fill: transparent;");

        botonJugar.setOpacity(0);
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), botonJugar);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), botonJugar);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);

        fieldNombreJugador.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.trim().isEmpty() && botonJugar.getOpacity() == 0) {
                fadeIn.play();
            }
            else if (newValue.trim().isEmpty() && botonJugar.getOpacity() == 1) {
                fadeOut.play();
            }
        });

        botonJugar.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), botonJugar);
            scaleUp.setToX(1.1);
            scaleUp.setToY(1.1);
            scaleUp.play();
        });

        botonJugar.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), botonJugar);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            scaleDown.play();
        });

        ControladorPartida controlador = new ControladorPartida(stage);

        botonJugar.setOnAction(event -> {
            String nombreJugador= fieldNombreJugador.getText().trim();
            try {
                controlador.handle(nombreJugador);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        VBox contenedorIngresoNombre = new VBox(10);
        contenedorIngresoNombre.setAlignment(Pos.CENTER);
        contenedorIngresoNombre.getChildren().addAll(textoAgregarJugador, fieldNombreJugador, botonJugar);

        // Configuración del FlowPane
        this.root.setAlignment(Pos.CENTER);
        this.root.setPrefSize(width, height);
        this.root.getChildren().add(contenedorIngresoNombre);
    }



}
