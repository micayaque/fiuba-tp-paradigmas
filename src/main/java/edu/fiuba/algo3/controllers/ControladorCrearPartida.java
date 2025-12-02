package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Catan;
import edu.fiuba.algo3.modelo.Color;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.ManagerTurno;
import edu.fiuba.algo3.modelo.Tablero.Factory.TableroFactory;
import edu.fiuba.algo3.vistas.PantallaPrincipal;
import edu.fiuba.algo3.vistas.vistas.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControladorCrearPartida implements EventHandler<ActionEvent> {
    private final Stage stage   ;
    private final PantallaPrincipal pantallaPrincipal;
    private final ArrayList<TextField> nombres;
    private final ArrayList<ColorPicker> colores;


    public ControladorCrearPartida(Stage stage, PantallaPrincipal pantallaPrincipal, ArrayList<TextField> nombresIngresados, ArrayList<ColorPicker> coloresElegidos) {
        this.stage = stage;
        this.pantallaPrincipal = pantallaPrincipal;
        this.nombres = nombresIngresados;
        this.colores = coloresElegidos;

    }

    private void crearJugadores(Catan cantan){

        for (int i = 0; i < nombres.size(); i++) {

            String nombre = nombres.get(i).getText();

            // Validación simple: Si no puso nombre, le ponemos uno por defecto o lanzamos alerta
            if (nombre.trim().isEmpty()) {
                nombre = "Jugador " + (i + 1);
            }

            // Convertimos el color de JavaFX a tu color del Modelo
            // Nota: toString() de ColorPicker devuelve algo como "0xff0000ff"
            String hexColor = colores.get(i).getValue().toString();

            // Creamos el jugador y lo agregamos
            cantan.agregarJugador(new Jugador(nombre, new edu.fiuba.algo3.modelo.Color(hexColor)));
        }

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Catan catan = new Catan();
        crearJugadores(catan);
        try {

            catan.iniciarPartida();

            // 4. Creamos la Vista del Tablero
            // Le pasamos el 'juego' ya listo para que la vista pueda pedirle cosas (getTablero, getManager, etc.)
            VistaTablero2 vistaJuego = new VistaTablero2(stage, pantallaPrincipal, catan);

            // 5. Cambiamos la pantalla
            pantallaPrincipal.setCentro(vistaJuego);

        } catch (Exception e) {
            System.out.println("Error al iniciar la partida: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
