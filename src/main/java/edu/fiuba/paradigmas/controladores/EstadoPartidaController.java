package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.partida.ObservadorResultadoPartida;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.vistas.EstadoPartidaVista;

import java.util.List;

public class EstadoPartidaController implements ObservadorResultadoPartida {
    private final EstadoPartidaVista vista;
    private final Moderador moderador;

    public EstadoPartidaController(EstadoPartidaVista vista, List<Jugador> jugadores) {
        this.vista = vista;
        this.moderador = new Moderador(jugadores, new EmpateDiurnoSinEliminacion(), this);

        int ronda = this.moderador.numeroDeRonda();
        
        Fase faseActual = this.moderador.obtenerFaseActual();

        String textoFase = "";
        if (faseActual instanceof FaseDiurna) {
            textoFase = "Fase Diurna (Discusiones y Votación)";
        } else if (faseActual instanceof FaseNocturna) {
            textoFase = "Fase Nocturna (La Mafia Ataca...)";
        }

        this.vista.mostrarEstado(ronda, textoFase);
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