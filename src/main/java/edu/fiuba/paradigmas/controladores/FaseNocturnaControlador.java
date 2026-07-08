package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.bando.Mafia;
import edu.fiuba.paradigmas.modelo.fase.OrganizadorDeTurnosNocturnos;
import edu.fiuba.paradigmas.modelo.historial.*;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.vistas.fase.PaseDispositivoVista;
import edu.fiuba.paradigmas.vistas.fase.ReporteFaseVista;
import edu.fiuba.paradigmas.vistas.componentes.reportefase.SeccionNoticias;

import java.util.ArrayList;
import java.util.List;

public class FaseNocturnaControlador extends FaseControlador {

    private final List<Memento> eventosDeEstaNoche;

    public FaseNocturnaControlador(ControladorDeJuego orquestador, Moderador moderador) {
        super(orquestador, moderador);
        this.eventosDeEstaNoche = new ArrayList<>();
        OrganizadorDeTurnosNocturnos organizador = new OrganizadorDeTurnosNocturnos();
        java.util.Queue<Jugador> colaOrdenada = organizador.armarColaDeTurnos(this.jugadoresVivos);
        this.jugadoresVivos.clear();
        this.jugadoresVivos.addAll(colaOrdenada);
        this.moderador.comenzarFaseNocturna();
        this.mostrarPantallaDePase();
    }

    @Override
    protected void mostrarPantallaDePase() {
        Jugador jugadorEnTurno = this.jugadoresVivos.get(this.indiceActual);
        PaseDispositivoVista vistaPase = new PaseDispositivoVista(jugadorEnTurno.nombre());
        vistaPase.configurarBotonAvanzar(this::mostrarPantallaDeAccion);
        this.orquestador.cambiarEscena(vistaPase);
    }

    private void mostrarPantallaDeAccion() {
        Jugador jugadorEnTurno = this.jugadoresVivos.get(this.indiceActual);
        ControladorDeTurnoNocturno turno = new ControladorDeTurnoNocturno(jugadorEnTurno, this.moderador, this);
        turno.configurarPantalla();
    }

    public void registrarEventoNocturno(Memento m) {
        this.eventosDeEstaNoche.add(m);
    }

    @Override
    protected void finalizarFase() {
        List<Memento> todosLosEventos = new ArrayList<>();
        todosLosEventos.add(this.moderador.resolverVotacion());
        todosLosEventos.addAll(this.eventosDeEstaNoche);
        String textoAsesinato = "No hubo asesinatos reportados.";
        String textoProteccion = "No se requirieron intervenciones médicas.";
        String textoInvestigacion = "Las autoridades no encontraron actividades sospechosas.";
        boolean huboMuertos = false;
        for (Memento m : todosLosEventos) {
            Memento contenido = desenvolver(m);
            if (contenido instanceof MementoDeEliminacion) {
                MementoDeEliminacion mEliminacion  = (MementoDeEliminacion) contenido;
                huboMuertos = true;
                String rol = this.traductorDeRoles.traducirRolDe(mEliminacion.victima());
                textoAsesinato = "¡Tragedia! " + mEliminacion.victima().nombre() + " fue eliminado. \n Su rol era: " + rol.toUpperCase();
            } else if (contenido instanceof MementoDeProteccion) {
                MementoDeProteccion mProt = (MementoDeProteccion) contenido;
                textoProteccion = "El médico intervino con éxito y salvó a " + mProt.jugadorProtegido().nombre() + " de un destino terrible.";
            } else if (contenido instanceof MementoDeInvestigacion) {
                MementoDeInvestigacion inv = (MementoDeInvestigacion) contenido;
                String bando = inv.bandoDescubierto() instanceof Mafia ? "MAFIA" : "CIUDADANOS";
                textoInvestigacion = "¡Rumores dicen que el Detective encontró pruebas contra " + inv.sospechoso().nombre() + " \n (Bando: " + bando + ")!";
            }
        }
        List<SeccionNoticias> secciones = List.of(
                new SeccionNoticias("💀 ASESINATOS", textoAsesinato, huboMuertos),
                new SeccionNoticias("🛡 PROTECCION", textoProteccion, false),
                new SeccionNoticias("🔍 INVESTIGACION", textoInvestigacion, false)
        );
        String titular = huboMuertos ? "EL TERROR GOLPEA \n LA ALDEA" : "NOCHE DE PAZ EN \n LA ALDEA";
        String bajada = huboMuertos ? "Una sombra oscura se cierne sobre la aldea" : "Los ciudadanos disfrutan de una noche de descanso";
        ReporteFaseVista vistaPeriodico = new ReporteFaseVista("Edición Matutina • Reporte Especial", titular, bajada, secciones, "COMENZAR DÍA");
        if (!this.procesarFinDeJuego(vistaPeriodico)) {
            vistaPeriodico.configurarBotonContinuar(() -> {
                this.moderador.comenzarFaseDiurna();
                new FaseDiurnaControlador(this.orquestador, this.moderador);
            });
        }
        this.orquestador.cambiarEscena(vistaPeriodico);
    }
}