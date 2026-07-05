package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.vistas.FaseDiurnaVista;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FaseDiurnaController {

    private final FaseDiurnaVista vista;
    private final ControladorDeJuego orquestador;
    private final Moderador moderador;
    private final Queue<Jugador> turnosPendientes;

    private boolean diaResuelto;

    public FaseDiurnaController(FaseDiurnaVista vista, ControladorDeJuego orquestador, Moderador moderador) {
        this.vista = vista;
        this.orquestador = orquestador;
        this.moderador = moderador;
        this.diaResuelto = false;

        List<Jugador> vivos = this.moderador.jugadoresVivos();
        this.vista.cargarOpciones(vivos);

        this.turnosPendientes = new LinkedList<>(vivos);
        this.avanzarTurno();
    }

    public void avanzarTurno() {
        if (this.diaResuelto) {
            return;
        }
        Jugador jugadorActivo = this.turnosPendientes.poll();
        if (jugadorActivo != null) {
            this.vista.iniciarTurnoOcultoDe(jugadorActivo.nombre());
            this.vista.setTitulo("Votación de " + jugadorActivo.nombre());
            this.vista.configurarBotonVotar(() -> {
                Jugador sospechoso = this.vista.obtenerJugadorSeleccionado();
                try {
                    this.moderador.registrarVoto(jugadorActivo, sospechoso);
                    this.avanzarTurno();
                } catch (RuntimeException excepcion) {
                    this.vista.mostrarMensaje(excepcion.getMessage());
                }
            });
        } else {
            this.diaResuelto = true;
            ResultadoFase resultado = this.moderador.resolverVotacion();
            this.moderador.comenzarFaseNocturna();
            this.orquestador.irAEstadoPartidaPreNoche(resultado);
        }
    }
}