package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.vistas.FaseDiurnaVista;

import java.util.LinkedList;
import java.util.List;

public class FaseDiurnaController extends ControladorDeFasePorTurnos {

    private final FaseDiurnaVista vista;

    public FaseDiurnaController(FaseDiurnaVista vista, ControladorDeJuego orquestador, Moderador moderador) {
        super(orquestador, moderador, new LinkedList<>(moderador.jugadoresVivos()));
        this.vista = vista;

        this.avanzarTurno();
    }

    @Override
    protected void configurarTurnoPara(Jugador jugadorActivo, List<Jugador> elegibles) {
        this.vista.cargarOpciones(elegibles);

        this.vista.iniciarTurnoDe(jugadorActivo.nombre());
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
    }

    @Override
    protected void resolverFase() {
        ResultadoFase resultado = this.moderador.resolverVotacion();
        this.moderador.comenzarFaseNocturna();
        this.orquestador.irAEstadoPartidaPreNoche(resultado);
    }
}