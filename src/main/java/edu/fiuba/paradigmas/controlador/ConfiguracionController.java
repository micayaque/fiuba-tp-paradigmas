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
import java.util.Random;

public class ConfiguracionController implements AccionIniciarJuego {
    private final ConfiguracionVista vista;
    private final App app;
    private final CreadorDeJugadores modelo = new CreadorDeJugadores(new Random());

    public ConfiguracionController(ConfiguracionVista vista, App app){
        this.vista = vista;
        this.app = app;

        this.vista.alPresionarIniciar(this);
    }

    public void iniciar(List<String> nombres, int cantidadMafiosos, boolean usaPadrino, boolean usaDetective, boolean usaMedico, boolean usaSheriff){
        List<Rol> roles = generarMazoConfigurado(nombres.size(), cantidadMafiosos, usaPadrino, usaDetective, usaMedico, usaSheriff);

        try {
            List<Jugador> jugadoresCreados = modelo.crearPartida(nombres, roles);
            this.app.irARepartoDeRoles(jugadoresCreados);
        } catch (ComposicionInvalidaExcepcion | CantidadDeJugadoresInvalidaExcepcion | RepartoInvalidoExcepcion e) {
            this.vista.mostrarError(e.getMessage());
        }
    }

    private List<Rol> generarMazoConfigurado(int cantidadJugadores, int cantidadMafiosos, boolean usaPadrino, boolean usaDetective, boolean usaMedico, boolean usaSheriff){
        List<Rol> mazo = new ArrayList<>();

        for (int i = 0; i < cantidadMafiosos; i++) {
            mazo.add(new Mafioso());
        }

        if (usaPadrino) {
            mazo.add(new Padrino());
        }
        if (usaDetective) {
            mazo.add(new Detective());
        }
        if (usaMedico) {
            mazo.add(new Medico());
        }
        if (usaSheriff) {
            mazo.add(new Sheriff());
        }

        while (mazo.size() < cantidadJugadores) {
            mazo.add(new Ciudadano());
        }

        return mazo;
    }
}
