package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.RepartoVista;
import edu.fiuba.paradigmas.vistas.modelo.JugadorEnReparto;

import java.util.List;

public class RepartoController {
    private final RepartoVista vista;
    private final App app;
    private final List<Jugador> jugadores;

    private int indiceActual = 0;
    private boolean mostrandoRol = false;

    public RepartoController(RepartoVista vista, App app, List<Jugador> jugadores) {
        this.vista = vista;
        this.app = app;
        this.jugadores = jugadores;

        this.vista.alPresionarBoton(this::avanzar);

        mostrarTurnoActual();
    }

    private void avanzar() {
        if (!mostrandoRol) {
            Jugador jugadorActual = jugadores.get(indiceActual);
            vista.mostrarRol(JugadorEnReparto.visible(jugadorActual));
            mostrandoRol = true;
        } else {
            indiceActual++;
            mostrandoRol = false;
            if (indiceActual < jugadores.size()) {
                mostrarTurnoActual();
            }
        }
    }

    private void mostrarTurnoActual() {
        Jugador jugadorActual = jugadores.get(indiceActual);
        vista.mostrarPantallaOculta(JugadorEnReparto.oculta(jugadorActual));
    }
}
