package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ObservadorMazo;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.*;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;
import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.ConfiguracionVista;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ConfiguracionController implements ObservadorMazo {
    private final ConfiguracionVista vista;
    private final App app;

    Map<String, Supplier<Rol>> creadoresDeRoles = new HashMap<>();

    CreadorDeJugadores modelo = new CreadorDeJugadores(new Random());

    private final ValidadorDeComposicionDelMazo validadorMazo = new ValidadorDeComposicionDelMazo(this);

    public ConfiguracionController(ConfiguracionVista vista, App app) {
        this.vista = vista;
        this.app = app;

        this.inicializarCreadores();

        this.vista.alPresionarRepartirCartas(this::iniciarRepartoCartas);
        this.vista.escucharCambiosEnTiempoReal(this::notificarClicAlModelo);

        this.notificarClicAlModelo();
    }

    private void notificarClicAlModelo() {
        List<String> roles = vista.obtenerRoles();
        int cantJugadores = vista.obtenerNombres().size();
        int padrinos = (int) roles.stream().filter(r -> r.equals("Padrino")).count();
        int ciudadanos = (int) roles.stream().filter(r -> r.equals("Ciudadano")).count();
        int mafiosos   = (int) roles.stream().filter(r -> r.equals("Mafioso")).count();
        int especialesCiudadanos = (int) roles.stream().filter(r -> r.equals("Medico") || r.equals("Detective") || r.equals("Sheriff")).count();

        validadorMazo.evaluar(cantJugadores, ciudadanos, mafiosos, especialesCiudadanos, padrinos);
    }

    @Override
    public void mesaLiberada() {
        vista.limpiarBloqueosVisuales();
    }

    @Override
    public void topeGlobalAlcanzado() {
        int cantJugadores = vista.obtenerNombres().size();
        String msjTope = (cantJugadores == 0)
                ? "Agregá un jugador para habilitar el mazo."
                : "Agregá más jugadores para elegir más cartas.";
        vista.bloquearMazoSobrante(msjTope);
    }

    @Override
    public void topeMafiaAlcanzado() {
        String msjMafia = "Límite máximo de jugadores mafiosos alcanzado.";
        vista.bloquearTipoCarta("Mafioso", msjMafia);
        vista.bloquearTipoCarta("Padrino", msjMafia);
    }

    @Override
    public void topeEspecialesAlcanzado() {
        String msjEspecial = "Límite máximo de roles especiales para la partida alcanzado.";
        vista.bloquearTipoCarta("Medico", msjEspecial);
        vista.bloquearTipoCarta("Detective", msjEspecial);
        vista.bloquearTipoCarta("Sheriff", msjEspecial);
        vista.bloquearTipoCarta("Padrino", msjEspecial);
    }

    private void iniciarRepartoCartas() {
        List<String> nombres = vista.obtenerNombres();
        List<String> rolesString = vista.obtenerRoles();

        String desempateElegido = vista.obtenerSistemaDesempate();
        SistemaDeEmpate estrategiaDesempate = desempateElegido.equals("Ballotage")
                ? new EmpateDiurnoBallotage()
                : new EmpateDiurnoSinEliminacion();

        try {
            List<Rol> roles = rolesString.stream()
                    .map(this::fabricarRol)
                    .collect(Collectors.toList());
            List<Jugador> jugadoresCreados = modelo.crearPartida(nombres, roles);
            this.app.irARepartoDeRoles(jugadoresCreados, estrategiaDesempate);

        } catch (CantidadDeJugadoresInvalidaExcepcion e) {
            this.vista.mostrarError("El juego acepta entre 5 y 12 jugadores.");
        } catch (RepartoInvalidoExcepcion e) {
            this.vista.mostrarError("La cantidad de jugadores no coincide con las cartas elegidas.");
        } catch (DemasiadosMafiososExcepcion e) {
            this.vista.mostrarError("Hay demasiados integrantes de la mafia para esta cantidad de jugadores.");
        } catch (ExcesoDeRolesEspecialesExcepcion e) {
            this.vista.mostrarError("Límite máximo de roles especiales para la partida superado.");
        } catch (FaltaDeMafiaExcepcion e) {
            this.vista.mostrarError("Debe haber al menos un integrante de la Mafia en la partida.");
        } catch (ComposicionInvalidaExcepcion e) {
            this.vista.mostrarError(e.getMessage());
        }

    }

    private Rol fabricarRol(String nombreRol) {
        Supplier<Rol> fabrica = creadoresDeRoles.get(nombreRol);
        return fabrica.get();
    }

    private void inicializarCreadores() {
        creadoresDeRoles.put("Ciudadano", Ciudadano::new);
        creadoresDeRoles.put("Mafioso", Mafioso::new);
        creadoresDeRoles.put("Medico", Medico::new);
        creadoresDeRoles.put("Detective", Detective::new);
        creadoresDeRoles.put("Sheriff", Sheriff::new);
        creadoresDeRoles.put("Padrino", Padrino::new);
    }
}