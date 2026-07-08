package edu.fiuba.paradigmas.vista.componentes;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.Random;

public class FondoEstrellas extends Pane {

    public FondoEstrellas(String colorFondoHex, String colorBrilloHex) {

        if (colorFondoHex.equals("TRANSPARENT")) {
            this.setStyle("-fx-background-color: transparent;");
        } else {
            this.setStyle("-fx-background-color: " + colorFondoHex + ";");
        }

        Random random = new Random();

        for (int i = 0; i < 50; i++) {
            Circle estrella = new Circle(Math.random() * 2.0);
            estrella.setFill(Color.WHITE);
            estrella.setOpacity(0.2 + random.nextDouble() * 0.6);
            estrella.setLayoutX(random.nextInt(600));
            estrella.setLayoutY(random.nextInt(1000));

            if (!colorBrilloHex.isEmpty()) {
                DropShadow brillo = new DropShadow();
                brillo.setColor(Color.web(colorBrilloHex));
                brillo.setRadius(10);
                brillo.setSpread(0.5);
                estrella.setEffect(brillo);
            }

            this.getChildren().add(estrella);
        }
    }
}