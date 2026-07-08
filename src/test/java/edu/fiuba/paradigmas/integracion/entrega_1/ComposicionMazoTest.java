package edu.fiuba.paradigmas.integracion.entrega_1;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.CantidadDeJugadoresInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.ComposicionInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.rol.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class ComposicionMazoTest {

    private List<String> nombres(int cantidad) {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            nombres.add("Jugador " + i);
        }
        return nombres;
    }

    @Test
    public void con5Jugadores_1MafiosoYUnEspecialEsValido() {
        List<Rol> roles = List.of(new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano(), new Ciudadano());

        assertDoesNotThrow(() -> new CreadorDeJugadores(new Random()).crearPartida(nombres(roles.size()), roles));
    }

    @Test
    public void con5Jugadores_2MafiososTambienEsValido() {
        List<Rol> roles = List.of(new Mafioso(), new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano());

        assertDoesNotThrow(() -> new CreadorDeJugadores(new Random()).crearPartida(nombres(roles.size()), roles));
    }

    @Test
    public void con7Jugadores_2MafiososDetectiveYMedicoEsValido() {
        List<Rol> roles = List.of(new Mafioso(), new Mafioso(), new Detective(), new Medico(),
                new Ciudadano(), new Ciudadano(), new Ciudadano());

        assertDoesNotThrow(() -> new CreadorDeJugadores(new Random()).crearPartida(nombres(roles.size()), roles));
    }

    @Test
    public void con10Jugadores_3DeMafiaConPadrinoYTodosLosEspecialesEsValido() {
        List<Rol> roles = List.of(new Mafioso(), new Mafioso(), new Padrino(),
                new Detective(), new Medico(), new Sheriff(),
                new Ciudadano(), new Ciudadano(), new Ciudadano(), new Ciudadano());

        assertDoesNotThrow(() -> new CreadorDeJugadores(new Random()).crearPartida(nombres(roles.size()), roles));
    }

    @Test
    public void laMafiaEnMayoriaEsRechazada() {
        List<Rol> roles = List.of(new Mafioso(), new Mafioso(), new Padrino(),
                new Detective(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class,
                () -> new CreadorDeJugadores(new Random()).crearPartida(nombres(roles.size()), roles));
    }

    @Test
    public void demasiadosEspecialesParaPocosJugadoresEsRechazado() {
        List<Rol> roles = List.of(new Mafioso(), new Detective(), new Medico(),
                new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class,
                () -> new CreadorDeJugadores(new Random()).crearPartida(nombres(roles.size()), roles));
    }

    @Test
    public void dosDelMismoRolEspecialEsRechazado() {
        List<Rol> roles = List.of(new Mafioso(), new Detective(), new Detective(),
                new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class,
                () -> new CreadorDeJugadores(new Random()).crearPartida(nombres(roles.size()), roles));
    }

    @Test
    public void menosDe5JugadoresEsRechazado() {
        List<Rol> roles = List.of(new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano());

        assertThrows(CantidadDeJugadoresInvalidaExcepcion.class,
                () -> new CreadorDeJugadores(new Random()).crearPartida(nombres(roles.size()), roles));
    }
}