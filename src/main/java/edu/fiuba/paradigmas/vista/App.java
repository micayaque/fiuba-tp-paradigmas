package edu.fiuba.paradigmas.vista;

import edu.fiuba.paradigmas.controlador.JuegoControlador;
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

        JuegoControlador controlador = new JuegoControlador(stage);
        controlador.irABienvenida();

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}