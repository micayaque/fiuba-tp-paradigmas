package edu.fiuba.algo3.vistas;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import edu.fiuba.algo3.vistas.escenas.*;

import java.util.Objects;


public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Balatro");
        Image icono = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icono.jpg")));
        stage.getIcons().add(icono);
        stage.setResizable(false);
        VistaMenu vistaMenu = new VistaMenu(stage, 1280, 720);
        stage.setScene(vistaMenu);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}