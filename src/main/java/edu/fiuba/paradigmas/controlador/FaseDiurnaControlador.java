package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeBallotage;
import edu.fiuba.paradigmas.modelo.historial.MementoDeEliminacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.vista.fase.PaseDispositivoVista;
import edu.fiuba.paradigmas.vista.fase.diurna.DebateVista;
import edu.fiuba.paradigmas.vista.fase.diurna.VotacionDiurnaVista;
import edu.fiuba.paradigmas.vista.fase.ReporteFaseVista;
import edu.fiuba.paradigmas.vista.componentes.reportefase.SeccionNoticias;

import java.util.ArrayList;
import java.util.List;

public class FaseDiurnaControlador extends FaseControlador {

    private final List<Jugador> candidatosAVotar;

    public FaseDiurnaControlador(JuegoControlador orquestador, Moderador moderador) {
        super(orquestador, moderador);
        this.candidatosAVotar = new ArrayList<>(this.jugadoresVivos);
        this.mostrarPantallaDebate();
    }

    public FaseDiurnaControlador(JuegoControlador orquestador, Moderador moderador, List<Jugador> empatados) {
        super(orquestador, moderador);
        this.candidatosAVotar = new ArrayList<>(empatados);
        this.mostrarPantallaDebate();
    }

    private void mostrarPantallaDebate() {
        DebateVista vistaDebate = new DebateVista();
        vistaDebate.configurarBotonIniciar(() -> {
            this.indiceActual = 0;
            this.mostrarPantallaDePase();
        });
        this.orquestador.cambiarEscena(vistaDebate);
    }

    @Override
    protected void mostrarPantallaDePase() {
        Jugador jugadorEnTurno = this.jugadoresVivos.get(this.indiceActual);
        PaseDispositivoVista vistaPase = new PaseDispositivoVista(jugadorEnTurno.nombre());
        vistaPase.configurarBotonAvanzar(this::mostrarPantallaDeVotacion);
        this.orquestador.cambiarEscena(vistaPase);
    }

    private void mostrarPantallaDeVotacion() {
        Jugador votante = this.jugadoresVivos.get(this.indiceActual);
        List<String> nombresCandidatos = extraerNombres(this.candidatosAVotar);
        boolean esBallotage = this.candidatosAVotar.size() < this.jugadoresVivos.size();
        VotacionDiurnaVista vistaVotacion = new VotacionDiurnaVista(nombresCandidatos, esBallotage);
        vistaVotacion.configurarAcciones(true, seleccion -> {
            try {
                if (!seleccion.equals("Abstenerse")) {
                    Jugador acusado = buscarJugadorPorNombre(seleccion);
                    this.moderador.registrarVoto(votante, acusado);
                }
                this.avanzarAlSiguienteTurno();
            } catch (RuntimeException excepcionDelDominio) {
                vistaVotacion.mostrarAdvertencia(excepcionDelDominio.getMessage());
            }
        });
        this.orquestador.cambiarEscena(vistaVotacion);
    }

    @Override
    protected void finalizarFase() {
        String textoEjecucion = "El pueblo no pudo llegar a un consenso. Hoy no hubo ejecuciones.";
        boolean huboMuertos = false;
        Memento mementoVotacion = this.moderador.resolverVotacion();
        Memento contenidoVotacion = desenvolver(mementoVotacion);

        if (contenidoVotacion instanceof MementoDeBallotage) {
            MementoDeBallotage mementoBallotage = (MementoDeBallotage) contenidoVotacion;
            List<Jugador> empatados = mementoBallotage.empatados();
            List<String> nombresEmpatados = extraerNombres(empatados);

            List<SeccionNoticias> secciones = new ArrayList<>();
            secciones.add(new SeccionNoticias(
                    "⚖ EMPATE DETECTADO",
                    "¡El pueblo está dividido! Se requiere una segunda vuelta electoral entre: " + String.join(" vs ", nombresEmpatados),
                    false
            ));

            ReporteFaseVista vistaPeriodico = getReporteFaseVista(secciones, empatados);

            this.orquestador.cambiarEscena(vistaPeriodico);
            return;
        }

        if (contenidoVotacion instanceof MementoDeEliminacion) {
            MementoDeEliminacion memento = (MementoDeEliminacion) contenidoVotacion;
            huboMuertos = true;
            String nombreMuerto = memento.victima().nombre();
            String rolMuerto = this.traductorDeRoles.traducirRolDe(memento.victima());
            textoEjecucion = "¡El pueblo ha hablado! \n " + nombreMuerto + " fue ejecutado a petición popular. " +
                    "\nTras la inspección, se reveló que su verdadero papel era: " + rolMuerto.toUpperCase() + ".";
        }

        List<SeccionNoticias> seccionesNoticias = new ArrayList<>();
        seccionesNoticias.add(new SeccionNoticias("⚖ ELIMINACIÓN", textoEjecucion, huboMuertos));
        String titular = huboMuertos ? "JUSTICIA IMPARTIDA \n EN LA PLAZA" : "UN DÍA DE \n INDESICIÓN";
        String bajada = huboMuertos ? "El pueblo elimina a un sospechoso" : "Los ciudadanos regresan a sus hogares con miedo.";
        ReporteFaseVista vistaPeriodico = new ReporteFaseVista("Evening Edition", titular, bajada, seccionesNoticias, "COMENZAR NOCHE");

        boolean terminoElJuego = this.procesarFinDeJuego(vistaPeriodico);

        if (!terminoElJuego) {
            vistaPeriodico.configurarBotonContinuar(() -> {
                new FaseNocturnaControlador(this.orquestador, this.moderador);
            });
        }

        this.orquestador.cambiarEscena(vistaPeriodico);
    }

    private ReporteFaseVista getReporteFaseVista(List<SeccionNoticias> secciones, List<Jugador> empatados) {
        ReporteFaseVista vistaPeriodico = new ReporteFaseVista(
                "Edición Especial de la Tarde",
                "ESTANCAMIENTO EN\n EL PUEBLO",
                "Los ciudadanos deben votar de nuevo",
                secciones,
                "COMIENZA LA SEGUNDA VOTACIÓN"
        );

        vistaPeriodico.configurarBotonContinuar(() -> {
            new FaseDiurnaControlador(this.orquestador, this.moderador, empatados);
        });
        return vistaPeriodico;
    }
}