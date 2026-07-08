package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.rol.*;
import edu.fiuba.paradigmas.vistas.configuracion.CantidadJugadoresVista;
import edu.fiuba.paradigmas.vistas.configuracion.NombresJugadoresVista;
import edu.fiuba.paradigmas.vistas.configuracion.ConfiguracionRolesVista;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ConfiguracionControlador {

    private final ControladorDeJuego orquestador;
    private int cantidadJugadores;
    private List<String> nombresIngresados;

    public ConfiguracionControlador(ControladorDeJuego orquestador) {
        this.orquestador = orquestador;
        this.mostrarPaso1Cantidad();
    }

    private void mostrarPaso1Cantidad() {
        CantidadJugadoresVista vista = new CantidadJugadoresVista();
        vista.configurarBotonVolver(this.orquestador::irABienvenida);
        vista.cargarOpciones(5, 12, cantidadElegida -> {
            this.cantidadJugadores = cantidadElegida;
            this.mostrarPaso2Nombres();
        });
        this.orquestador.cambiarEscena(vista);
    }

    private void mostrarPaso2Nombres() {
        NombresJugadoresVista vista = new NombresJugadoresVista(this.cantidadJugadores);
        vista.configurarBotonVolver(this::mostrarPaso1Cantidad);
        vista.configurarBotonContinuar(nombres -> {
            this.nombresIngresados = nombres;
            this.mostrarPaso3Roles();
        });
        this.orquestador.cambiarEscena(vista);
    }

    private void mostrarPaso3Roles() {
        ConfiguracionRolesVista vista = new ConfiguracionRolesVista(this.cantidadJugadores);
        vista.configurarBotonVolver(this::mostrarPaso2Nombres);
        vista.configurarBotonIniciar(() -> {
            try {
                List<Rol> rolesElegidos = this.empaquetarRolesDesdeUI(
                        vista.getCantCiudadanos(), vista.getCantMafiosos(),
                        vista.getCantPadrinos(), vista.getCantSheriffs(),
                        vista.getCantMedicos(), vista.getCantDetectives()
                );
                CreadorDeJugadores creador = new CreadorDeJugadores(new Random());
                List<Jugador> jugadoresCreados = creador.crearPartida(this.nombresIngresados, rolesElegidos);
                SistemaDeEmpate estrategiaEmpate;
                if (vista.isBallotageActivado()) {
                    estrategiaEmpate = new EmpateDiurnoBallotage();
                } else {
                    estrategiaEmpate = new EmpateDiurnoSinEliminacion();
                }
                Moderador moderador = new Moderador(jugadoresCreados, estrategiaEmpate);
                this.orquestador.iniciarPartida(moderador);
            } catch (RuntimeException excepcionDelDominio) {
                vista.mostrarAdvertencia(excepcionDelDominio.getMessage());
            }
        });
        this.orquestador.cambiarEscena(vista);
    }

    private List<Rol> empaquetarRolesDesdeUI(int ciudadanos, int mafiosos, int padrinos, int sheriff, int medicos, int detectives) {
        List<Rol> bolsaDeRoles = new ArrayList<>();
        for (int i = 0; i < ciudadanos; i++) {
            bolsaDeRoles.add(new Ciudadano());
        }
        for (int i = 0; i < mafiosos; i++) {
            bolsaDeRoles.add(new Mafioso());
        }
        for (int i = 0; i < padrinos; i++) {
            bolsaDeRoles.add(new Padrino());
        }
        for (int i = 0; i < sheriff; i++) {
            bolsaDeRoles.add(new Sheriff());
        }
        for (int i = 0; i < medicos; i++) {
            bolsaDeRoles.add(new Medico());
        }
        for (int i = 0; i < detectives; i++) {
            bolsaDeRoles.add(new Detective());
        }
        return bolsaDeRoles;
    }
}