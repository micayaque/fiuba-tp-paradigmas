package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.RepartoVista;

import java.util.List;

public class RepartoController {
    private final RepartoVista vista;
    private final App app;
    private final List<Jugador> jugadores;
    private final TraductorVisualRol traductorRol = new TraductorVisualRol();

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
            vista.mostrarRol(jugadorActual.nombre(), traductorRol.traducirRolDe(jugadorActual));
            mostrandoRol = true;
        } else {
            indiceActual++;
            mostrandoRol = false;
            if (indiceActual < jugadores.size()) {
                mostrarTurnoActual();
            } else {
                this.app.irAEstadoDePartida(this.jugadores);
            }
        }
    }

    private void mostrarTurnoActual() {
        Jugador jugadorActual = jugadores.get(indiceActual);
        vista.mostrarPantallaOculta(jugadorActual.nombre());
    }
}
