package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.EstadoPartidaVista;

import java.util.List;

public class EstadoPartidaController {
    private final EstadoPartidaVista vista;
    private final App app;
    private final Moderador moderador;

    public EstadoPartidaController(EstadoPartidaVista vista, App app, List<Jugador> jugadores) {
        this.vista = vista;
        this.app = app;
        this.moderador = new Moderador(jugadores, new EmpateDiurnoSinEliminacion());

        this.vista.mostrarEstado(this.moderador.numeroDeRonda(), this.moderador.faseActual());
    }
}