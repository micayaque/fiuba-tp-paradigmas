package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.JugadorYaTieneRol;
import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.mazo.Mazo;
import edu.fiuba.algo3.modelo.roles.Ciudadano;
import edu.fiuba.algo3.modelo.roles.Mafioso;
import edu.fiuba.algo3.modelo.roles.Rol;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class RepartoTest {

    private List<Jugador> crearJugadores(int cantidad) {
        List<Jugador> jugadores = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }
        return jugadores;
    }

    private Map<Class<?>, Long> clasificarPorTipo(List<Rol> roles) {
        return roles.stream().collect(Collectors.groupingBy(Rol::getClass, Collectors.counting()));
    }

    @Test
    public void cadaJugadorRecibeExactamenteUnRol() {
        List<Jugador> jugadores = crearJugadores(7);

        new Mazo().asignarCartas(jugadores);

        for (Jugador jugador : jugadores) {
            assertTrue(jugador.tieneRol());
            assertNotNull(jugador.miRol());
        }
    }

    @Test
    public void seRepartenTodasLasCartasDelMazoSinPerdidasNiDuplicados() {
        List<Jugador> jugadores = crearJugadores(7);
        Mazo mazo = new Mazo();

        Map<Class<?>, Long> composicionEsperada = clasificarPorTipo(mazo.generarPara(7));
        mazo.asignarCartas(jugadores);

        Map<Class<?>, Long> composicionRepartida = jugadores.stream()
                .collect(Collectors.groupingBy(jugador -> jugador.miRol().getClass(), Collectors.counting()));

        assertEquals(composicionEsperada, composicionRepartida);
    }

    @Test
    public void elRepartoEsAleatorioEntrePartidas() {
        Set<Class<?>> rolesVistosEnLaPrimeraPosicion = new HashSet<>();

        for (int intento = 0; intento < 50; intento++) {
            List<Jugador> jugadores = crearJugadores(7);
            new Mazo().asignarCartas(jugadores);
            rolesVistosEnLaPrimeraPosicion.add(jugadores.get(0).miRol().getClass());
        }

        assertTrue(rolesVistosEnLaPrimeraPosicion.size() > 1,
                "El reparto deberia asignar distintos roles a un mismo jugador entre partidas");
    }

    @Test
    public void unJugadorNoPuedeRecibirDosRoles() {
        Jugador jugador = new Jugador("Ana", new Ciudadano());

        assertThrows(JugadorYaTieneRol.class, () -> jugador.asignarCarta(new Mafioso()));
    }
}
