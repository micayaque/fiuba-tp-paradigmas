package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.vistas.FaseDiurnaVista;

import java.util.LinkedList;
import java.util.List;

public class FaseDiurnaController extends ControladorDeFasePorTurnos {

    private final FaseDiurnaVista vista;
    private final List<Jugador> candidatos;

    public FaseDiurnaController(FaseDiurnaVista vista, ControladorDeJuego orquestador, Moderador moderador) {
        this(vista, orquestador, moderador, moderador.jugadoresVivos());
    }

    public FaseDiurnaController(FaseDiurnaVista vista, ControladorDeJuego orquestador, Moderador moderador, List<Jugador> candidatos) {
        super(orquestador, moderador, new LinkedList<>(candidatos));
        this.vista = vista;
        this.candidatos = candidatos;
        this.avanzarTurno();
    }

    @Override
    protected List<Jugador> candidatosValidos() {
        return this.candidatos;
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
            } catch (VotoInvalidoExcepcion excepcion) {
                this.vista.mostrarMensaje(excepcion.getMessage());
            }
        });
        this.vista.configurarBotonAbstenerse(this::avanzarTurno);
    }

    @Override
    protected void resolverFase() {
        Memento resultado = this.moderador.resolverVotacion();

        if (resultado instanceof MementoDeBallotage) {
            MementoDeBallotage ballotage = (MementoDeBallotage) resultado;
            this.orquestador.irAEstadoPartidaPreBallotage(resultado, ballotage.empatados());
        } else {
            this.moderador.avanzarFase();
            this.orquestador.irAEstadoPartidaPreNoche(resultado);
        }
    }
}