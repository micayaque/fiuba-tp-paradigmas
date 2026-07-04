package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.RepartoVista;

import java.util.List;
import java.util.Map;

public class RepartoController {
    private final RepartoVista vista;
    private final App app;
    private final List<Jugador> jugadores;
    private final TraductorVisualRol traductorRol;

    private final Map<String, String> descripcionesPorRol = Map.of(
            "Ciudadano", "Intentá descubrir a la mafia durante el día.",
            "Mafioso", "Eliminá a los ciudadanos durante la noche sin ser descubierto.",
            "Medico", "Protegé a tus compañeros durante la noche otorgándoles inmunidad.",
            "Detective", "Investigá a un jugador cada noche para descubrir su verdadero bando.",
            "Sheriff", "Revelá tu rol durante el día para ganar autoridad.",
            "Padrino", "Liderá a la mafia. Sos indetectable para el detective."
    );

    private int indiceActual;

    public RepartoController(RepartoVista vista, App app, List<Jugador> jugadores) {
        this.vista = vista;
        this.app = app;
        this.jugadores = jugadores;
        this.indiceActual = 0;
        this.traductorRol = new TraductorVisualRol();
        this.vista.alPresionarVerCarta(() -> this.mostrarCartaActual());
        this.vista.alPresionarOcultarCarta(() -> this.avanzarSiguienteJugador());
        this.prepararTurno();
    }

    private void prepararTurno() {
        Jugador jugadorActual = this.jugadores.get(this.indiceActual);
        int turnoVisual = this.indiceActual + 1;
        int totalJugadores = this.jugadores.size();
        this.vista.actualizarProgreso(turnoVisual, totalJugadores, jugadorActual.nombre());
    }

    private void mostrarCartaActual() {
        Jugador jugadorActual = this.jugadores.get(this.indiceActual);
        String nombreRol = this.traductorRol.traducirRolDe(jugadorActual);
        String archivoImagen = nombreRol.toLowerCase() + ".png";
        String descripcion = descripcionesPorRol.get(nombreRol);
        this.vista.mostrarCarta(archivoImagen, descripcion);
    }

    private void avanzarSiguienteJugador() {
        this.indiceActual++;
        if (this.indiceActual < this.jugadores.size()) {
            this.prepararTurno();
            this.vista.ocultarCarta();
        } else {
            this.app.irAEstadoDePartida(this.jugadores);
        }
    }
}