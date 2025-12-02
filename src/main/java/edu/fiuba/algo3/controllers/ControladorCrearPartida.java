package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.Color;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.ManagerTurno;
import edu.fiuba.algo3.modelo.SesionDeJuego;
import edu.fiuba.algo3.modelo.Tablero.Factory.TableroFactory;
import edu.fiuba.algo3.vistas.PantallaPrincipal;
import edu.fiuba.algo3.vistas.vistas.VistaPedirCantidadJugadores;
import edu.fiuba.algo3.vistas.vistas.VistaTablero;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ControladorCrearPartida implements EventHandler<ActionEvent> {
    private final Stage stage   ;
    private final PantallaPrincipal pantallaPrincipal   ;
    private final List<String> nombresJugadores;
    private final List<Color> coloresJugadores;
    private ArrayList<TextField> nombresIngresados;
    private ArrayList<ColorPicker> coloresElegidos;

    public ControladorCrearPartida(Stage stage, PantallaPrincipal pantallaPrincipal, ArrayList<TextField> nombresIngresados, ArrayList<ColorPicker> coloresElegidos) {
        this.stage = stage;
        this.pantallaPrincipal = pantallaPrincipal;
        this.nombresJugadores = new ArrayList<>();
        this.coloresJugadores = new ArrayList<>();
        this.nombresIngresados = nombresIngresados;
        this.coloresElegidos = coloresElegidos;


    }

    private void crearJugadores(ArrayList<TextField> nombresIngresados, ArrayList<ColorPicker> coloresElegidos) {
        for (int i = 0; i < nombresIngresados.size(); i++) {
            String nombre = nombresIngresados.get(i).getText();
            javafx.scene.paint.Color fxColor = coloresElegidos.get(i).getValue();

            String colorJUgador = fxColor.toString();
            System.out.println("colorJUgador: " + colorJUgador);
            System.out.println("nombreJUgador: " + nombre);
            nombresJugadores.add(nombre);
            coloresJugadores.add(new Color(colorJUgador));

        }
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        crearJugadores(this.nombresIngresados, this.coloresElegidos);
        SesionDeJuego sesion = SesionDeJuego.obtenerInstancia();
        sesion.iniciarPartida(nombresJugadores, coloresJugadores);
        pantallaPrincipal.setCentro(new VistaTablero(stage,pantallaPrincipal));
        if (!sesion.partidaIniciada()) {
            System.err.println("Error: No se pudo iniciar la partida");
        }
    }
}
