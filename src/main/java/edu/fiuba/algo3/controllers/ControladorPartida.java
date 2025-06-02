package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.controllers.Factory.FactoryComodines;
import edu.fiuba.algo3.controllers.Factory.FactoryDeMazo;
import edu.fiuba.algo3.controllers.Factory.FactoryDeMazo;
import edu.fiuba.algo3.controllers.Factory.FactoryDeTarot;
import edu.fiuba.algo3.controllers.Factory.FactoryRondas;
import edu.fiuba.algo3.modelo.Balatro.Balatro;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.ronda.Ronda;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.fiuba.algo3.modelo.GeneradorDeCartas.GeneradorDeCartas;

public class ControladorPartida {
    private Stage stage;
    private String jugador;

    public ControladorPartida(Stage stage) {
        this.stage = stage;

    }

    public void handle(String jugador) throws IOException {
        Balatro balatro = Balatro.juego();

        if (jugador.isEmpty()) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("El nombre del jugador no puede estar vacío.");
            alerta.showAndWait();
        } else {
            balatro.inicializadorDeBalatro("src/main/resources/balatro.json","src/main/resources/mazo.json", "src/main/resources/tarots.json", "src/main/resources/comodines.json", jugador);
        }
        ControladorPrincipal controlador = new ControladorPrincipal(stage);
        controlador.empezarPartida();
        }
    }

