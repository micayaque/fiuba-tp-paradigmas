package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.partida.ResultadoPartida;
import edu.fiuba.paradigmas.modelo.partida.VictoriaCiudadanos;
import edu.fiuba.paradigmas.modelo.partida.VictoriaMafia;
import edu.fiuba.paradigmas.vistas.fase.ReporteFaseVista;
import edu.fiuba.paradigmas.vistas.victoria.PantallaVictoriaVista;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public abstract class FaseControlador {

    protected final ControladorDeJuego orquestador;
    protected final Moderador moderador;
    protected final List<Jugador> jugadoresVivos;
    protected final TraductorVisualRol traductorDeRoles;
    protected int indiceActual;

    public FaseControlador(ControladorDeJuego orquestador, Moderador moderador) {
        this.orquestador = orquestador;
        this.moderador = moderador;
        this.jugadoresVivos = moderador.jugadoresVivos();
        this.traductorDeRoles = new TraductorVisualRol();
        this.indiceActual = 0;
    }

    protected void avanzarAlSiguienteTurno() {
        this.indiceActual++;
        if (this.indiceActual < this.jugadoresVivos.size()) {
            this.mostrarPantallaDePase();
        } else {
            this.finalizarFase();
        }
    }

    protected abstract void mostrarPantallaDePase();
    protected abstract void finalizarFase();

    protected Memento desenvolver(Memento memento) {
        if (memento instanceof MementoDeFase) {
            return desenvolver(((MementoDeFase) memento).contenido());
        }
        return memento;
    }

    protected List<String> extraerNombres(List<Jugador> lista) {
        List<String> nombres = new ArrayList<>();
        lista.forEach(j -> nombres.add(j.nombre()));
        return nombres;
    }

    protected Jugador buscarJugadorPorNombre(String nombre) {
        return this.jugadoresVivos.stream()
                .filter(j -> j.nombre().equals(nombre))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    protected Map<String, String> recopilarTodosLosRoles() {
        Map<String, String> rolesRevelados = new LinkedHashMap<>();
        List<Jugador> todosLosJugadores = new ArrayList<>();
        todosLosJugadores.addAll(this.moderador.jugadoresVivos());
        todosLosJugadores.addAll(this.moderador.jugadoresEliminados());
        todosLosJugadores.forEach(j -> rolesRevelados.put(j.nombre(), this.traductorDeRoles.traducirRolDe(j)));
        return rolesRevelados;
    }

    protected boolean procesarFinDeJuego(ReporteFaseVista vistaPeriodico) {
        ResultadoPartida estadoPartida = this.moderador.evaluarGanador();
        if (estadoPartida instanceof VictoriaMafia) {
            vistaPeriodico.configurarBotonContinuar(() -> mostrarPantallaVictoria(false));
            return true;
        } else if (estadoPartida instanceof VictoriaCiudadanos) {
            vistaPeriodico.configurarBotonContinuar(() -> mostrarPantallaVictoria(true));
            return true;
        }
        return false;
    }

    private void mostrarPantallaVictoria(boolean ganaronCiudadanos) {
        PantallaVictoriaVista vistaVictoria = new PantallaVictoriaVista(ganaronCiudadanos, recopilarTodosLosRoles());
        vistaVictoria.configurarBotonResumen(() -> this.orquestador.irAResumen(this.moderador));
        vistaVictoria.configurarBotonNuevoJuego(this.orquestador::volverAlMenu);
        this.orquestador.cambiarEscena(vistaVictoria);
    }
}