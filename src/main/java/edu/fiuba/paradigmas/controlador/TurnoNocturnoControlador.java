package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.rol.IdentificadorRol;
import edu.fiuba.paradigmas.vista.fase.nocturna.InactivoNocturnoVista;
import edu.fiuba.paradigmas.vista.fase.nocturna.ResultadoInvestigacionVista;
import edu.fiuba.paradigmas.vista.fase.nocturna.VotacionNocturnaVista;
import java.util.ArrayList;
import java.util.List;

public class TurnoNocturnoControlador implements IdentificadorRol {

    private final Jugador jugadorActivo;
    private final Moderador moderador;
    private final FaseNocturnaControlador controlador;
    private final List<String> nombresVivos;

    public TurnoNocturnoControlador(Jugador jugadorActivo, Moderador moderador, FaseNocturnaControlador controlador) {
        this.jugadorActivo = jugadorActivo;
        this.moderador = moderador;
        this.controlador = controlador;
        this.nombresVivos = new ArrayList<>();
        this.moderador.jugadoresVivos().forEach(j -> this.nombresVivos.add(j.nombre()));
    }

    public void configurarPantalla() {
        this.jugadorActivo.identificarRolEn(this);
    }

    @Override
    public void esMafioso() {
        VotacionNocturnaVista vista = new VotacionNocturnaVista("Mafia", "💀", "#ff416c", "Elige a quién eliminar", nombresVivos);
        vista.configurarAcciones(false, seleccion -> {
            try {
                Jugador victima = controlador.buscarJugadorPorNombre(seleccion);
                this.moderador.registrarVoto(this.jugadorActivo, victima);
                this.controlador.avanzarAlSiguienteTurno();
            } catch (RuntimeException excepcion) {
                vista.mostrarAdvertencia(excepcion.getMessage());
            }
        });
        this.controlador.orquestador.cambiarEscena(vista);
    }

    @Override
    public void esPadrino() {
        this.esMafioso();
    }

    @Override
    public void esMedico() {
        VotacionNocturnaVista vista = new VotacionNocturnaVista("Médico", "➕", "#00b894", "Elige a quién proteger", nombresVivos);
        vista.configurarAcciones(false, seleccion -> {
            try {
                Jugador protegido = controlador.buscarJugadorPorNombre(seleccion);
                this.controlador.registrarEventoNocturno(this.moderador.registrarProteccion(this.jugadorActivo, protegido));
                this.controlador.avanzarAlSiguienteTurno();
            } catch (RuntimeException ex) {
                vista.mostrarAdvertencia(ex.getMessage());
            }
        });
        this.controlador.orquestador.cambiarEscena(vista);
    }

    @Override
    public void esDetective() {
        VotacionNocturnaVista vista = new VotacionNocturnaVista("Detective", "🔍", "#3182ce", "Elige a quién investigar", nombresVivos);
        vista.configurarAcciones(false, seleccion -> {
            try {
                Jugador sospechoso = controlador.buscarJugadorPorNombre(seleccion);
                edu.fiuba.paradigmas.modelo.historial.Memento mementoBruto = this.moderador.registrarInvestigacion(this.jugadorActivo, sospechoso);
                this.controlador.registrarEventoNocturno(mementoBruto);
                edu.fiuba.paradigmas.modelo.historial.MementoDeInvestigacion inv = (edu.fiuba.paradigmas.modelo.historial.MementoDeInvestigacion) this.controlador.desenvolver(mementoBruto);
                String bando = inv.bandoDescubierto().getClass().getSimpleName();
                ResultadoInvestigacionVista vistaResultado = new ResultadoInvestigacionVista(sospechoso.nombre(), bando);
                vistaResultado.configurarBotonAvanzar(this.controlador::avanzarAlSiguienteTurno);
                this.controlador.orquestador.cambiarEscena(vistaResultado);
            } catch (RuntimeException ex) {
                vista.mostrarAdvertencia(ex.getMessage());
            }
        });

        this.controlador.orquestador.cambiarEscena(vista);
    }

    @Override
    public void esCiudadano() {
        InactivoNocturnoVista vistaInactiva = new InactivoNocturnoVista();
        vistaInactiva.configurarBotonAvanzar(this.controlador::avanzarAlSiguienteTurno);
        this.controlador.orquestador.cambiarEscena(vistaInactiva);
    }

    @Override
    public void esSheriff() {
        this.esCiudadano();
    }
}