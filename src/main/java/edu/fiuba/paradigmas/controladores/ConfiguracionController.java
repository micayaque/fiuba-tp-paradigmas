package edu.fiuba.paradigmas.controladores;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.CantidadDeJugadoresInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.ComposicionInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.RepartoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;
import edu.fiuba.paradigmas.vistas.App;
import edu.fiuba.paradigmas.vistas.ConfiguracionVista;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ConfiguracionController implements AccionIniciarJuego {
    private final ConfiguracionVista vista;
    private final App app;
    private final CreadorDeJugadores modelo = new CreadorDeJugadores(new Random());

    public ConfiguracionController(ConfiguracionVista vista, App app){
        this.vista = vista;
        this.app = app;

        this.vista.alPresionarIniciar(this);
    }

    public void iniciar(List<String> nombres, List<String> rolesString) {
        List<Rol> roles = rolesString.stream()
                .map(this::traducirStringARol)
                .collect(Collectors.toList());

        try {
            List<Jugador> jugadoresCreados = modelo.crearPartida(nombres, roles);
            this.app.irARepartoDeRoles(jugadoresCreados);
        } catch (ComposicionInvalidaExcepcion | CantidadDeJugadoresInvalidaExcepcion | RepartoInvalidoExcepcion e) {
            this.vista.mostrarError(e.getMessage());
        }
    }

    private Rol traducirStringARol(String nombreRol) {
        switch (nombreRol) {
            case "Ciudadano": return new Ciudadano();
            case "Mafioso": return new Mafioso();
            case "Medico": return new Medico();
            case "Detective": return new Detective();
            case "Sheriff": return new Sheriff();
            case "Padrino": return new Padrino();
            default: throw new IllegalArgumentException("Rol desconocido: " + nombreRol);
        }
    }
}
