package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.partida.ObservadorResultadoPartida;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.EstadoPartidaVista;

import java.util.List;

public class EstadoPartidaController implements ObservadorResultadoPartida {
    private final EstadoPartidaVista vista;
    private final Moderador moderador;
    private final TraductorVisualRol traductorRol;

    public EstadoPartidaController(EstadoPartidaVista vista, App app, List<Jugador> jugadores) {
        this.vista = vista;
        this.traductorRol = new TraductorVisualRol();
        this.moderador = new Moderador(jugadores, new EmpateDiurnoSinEliminacion(), this);
        this.actualizarPantalla();
    }

    private void actualizarPantalla() {
        int ronda = this.moderador.numeroDeRonda();
        this.vista.limpiarTablero(ronda);
        List<Jugador> vivos = this.moderador.jugadoresVivos();
        List<Jugador> eliminados = this.moderador.jugadoresEliminados();

        for (Jugador jugador : vivos) {
            this.vista.agregarJugadorVivo(jugador.nombre());
        }

        for (Jugador jugador : eliminados) {
            String nombreRol = this.traductorRol.traducirRolDe(jugador);
            String archivoImagen = nombreRol.toLowerCase() + ".png";
            this.vista.agregarJugadorEliminado(jugador.nombre(), nombreRol, archivoImagen);
        }
    }

    @Override
    public void anunciarVictoriaMafia() {
        this.vista.mostrarGanador("Mafia");
    }

    @Override
    public void anunciarVictoriaCiudadanos() {
        this.vista.mostrarGanador("Ciudadanos");
    }
}