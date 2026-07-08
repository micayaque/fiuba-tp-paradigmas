package edu.fiuba.paradigmas.vistas;

import edu.fiuba.paradigmas.controladores.ControladorDeJuego;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("La Mafia");

        stage.setWidth(480);
        stage.setHeight(830);

        stage.setMinWidth(480);
        stage.setMinHeight(830);

        ControladorDeJuego controlador = new ControladorDeJuego(stage);
        controlador.irABienvenida();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}