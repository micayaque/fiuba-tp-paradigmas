package edu.fiuba.paradigmas.controlador;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.CantidadDeJugadoresInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.ComposicionInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.RepartoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;
import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.ConfiguracionVista;

import java.util.ArrayList;
import java.util.List;

public class ConfiguracionController {
    private final ConfiguracionVista vista;
    private final App app;
    private final CreadorDeJugadores modelo = new CreadorDeJugadores();

    public ConfiguracionController(ConfiguracionVista vista, App app){
        this.vista = vista;
        this.app = app;

        this.vista.alPresionarIniciar(this::iniciar);
    }

    public void iniciar(){
        List<String> nombres = vista.obtenerNombres();

        List<Rol> roles = generarMazoPorDefecto(nombres.size());

        try {
            List<Jugador> jugadoresCreados = modelo.crearPartida(nombres, roles);
            this.app.irARepartoDeRoles(jugadoresCreados);
        } catch (ComposicionInvalidaExcepcion | CantidadDeJugadoresInvalidaExcepcion | RepartoInvalidoExcepcion e) {
            this.vista.mostrarError(e.getMessage());
        }
    }

    private List<Rol> generarMazoPorDefecto(int cantidadJugadores){
        List<Rol> mazo = new ArrayList<>();

        if (cantidadJugadores < 5 ||  cantidadJugadores > 12){
        }

        else if (cantidadJugadores <= 6){
            mazo.add(new Mafioso());
            mazo.add(new Detective());
        }
        else if (cantidadJugadores <= 9){
            mazo.add(new Mafioso());
            mazo.add(new Mafioso());
            mazo.add(new Detective());
            mazo.add(new Medico());
        }
        else {
            mazo.add(new Mafioso());
            mazo.add(new Mafioso());
            mazo.add(new Mafioso());
            mazo.add(new Detective());
            mazo.add(new Medico());
            mazo.add(new Padrino());
            mazo.add(new Sheriff());
        }

        while (mazo.size() < cantidadJugadores) {
            mazo.add(new Ciudadano());
        }

        return mazo;
    }
}
