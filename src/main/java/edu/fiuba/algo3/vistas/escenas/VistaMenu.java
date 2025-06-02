package edu.fiuba.algo3.vistas.escenas;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import java.util.Objects;


public class VistaMenu extends Scene {
    private Stage stage;

    public VistaMenu(Stage stage, double width, double height) {
        super(new Pane(), width, height);
        this.stage = stage;
        Pane root = (Pane) this.getRoot();
        BackgroundImage imagenFondo = new BackgroundImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/background.jpg"))), BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background fondo = new Background(imagenFondo);
        root.setBackground(fondo);

        ImageView imagenEncima = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/BALATRO1.png"))));
        double anchoImagen = imagenEncima.getImage().getWidth();
        double altoImagen = imagenEncima.getImage().getHeight();

        double anchoPane = root.getWidth();
        double altoPane = root.getHeight();

        double posX = (anchoPane - anchoImagen) / 2;
        double posY = (altoPane - altoImagen) / 2;

        imagenEncima.setLayoutX(posX);
        imagenEncima.setLayoutY(posY);

        root.getChildren().add(imagenEncima);

        Button botonjugar = new Button("", imagenEncima);
        botonjugar.setStyle("-fx-background-color: transparent;");
        botonjugar.setLayoutX(posX);
        botonjugar.setLayoutY(posY);

        botonjugar.setOnMouseEntered(e -> {
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), imagenEncima);
            scaleUp.setToX(1.2); // Aumentar el tamaño en un 20%
            scaleUp.setToY(1.2); // Aumentar el tamaño en un 20%
            scaleUp.play();
        });

        botonjugar.setOnMouseExited(e -> {
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), imagenEncima);
            scaleDown.setToX(1.0); // Volver al tamaño original (sin aumento)
            scaleDown.setToY(1.0); // Volver al tamaño original (sin aumento)
            scaleDown.play();
        });

        root.getChildren().add(botonjugar);
        botonjugar.setOnAction( e -> {
            stage.setScene(new VistaPartida(stage, width, height));
        });
    }
}