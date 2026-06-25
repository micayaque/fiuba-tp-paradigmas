package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.vistas.EstadoPartidaVista;

import java.util.List;

public class EstadoPartidaController {
    private final EstadoPartidaVista vista;
    private final Moderador moderador;

    public EstadoPartidaController(EstadoPartidaVista vista, List<Jugador> jugadores) {
        this.vista = vista;
        this.moderador = new Moderador(jugadores, new EmpateDiurnoSinEliminacion());

        int ronda = this.moderador.numeroDeRonda();
        
        TraductorVisualFase traductorFase = new TraductorVisualFase();
        
        String textoFase = traductorFase.traducirDesde(this.moderador);

        this.vista.mostrarEstado(ronda, textoFase);
    }
}