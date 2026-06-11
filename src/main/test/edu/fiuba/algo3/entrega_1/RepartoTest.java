package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Rol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class RepartoTest {

    private List<Jugador> jugadores;

    @BeforeEach
    public void setUp() {
        this.jugadores = JugadorTestFactory.crearJugadores(12);
    }

    @Test
    public void elRepartoEsAleatorioEntrePartidas() {
        Set<List<Class<?>>> asignacionesDeRolesVistas = new HashSet<>();

        for (int intento = 0; intento < 50; intento++) {
            List<Jugador> jugadores = JugadorTestFactory.crearJugadores(12);
            List<Class<?>> rolesAsignados = jugadores.stream()
                                                        .map(j -> j.rol().getClass())
                                                        .collect(Collectors.toList());
            asignacionesDeRolesVistas.add(rolesAsignados);
        }

        assertTrue(asignacionesDeRolesVistas.size() > 1,
                "El reparto deberia asignar distintos roles a un mismo jugador entre partidas");
    }

    @Test
    public void cadaJugadorRecibeExactamenteUnRol() {
        for (Jugador jugador : jugadores) {
            assertNotNull(jugador.rol());
        }
    }

    @Test
    public void seAsignanInstanciasUnicasDeRolACadaJugadorYNoQuedanSinRol() {
        Set<Rol> instanciasDeRolesAsignados = jugadores.stream()
                                                       .map(Jugador::rol)
                                                       .collect(Collectors.toSet());

        assertFalse(instanciasDeRolesAsignados.contains(null),
                "Ningún jugador debería haberse quedado sin rol");

        assertEquals(jugadores.size(), instanciasDeRolesAsignados.size(), 
                "Cada jugador debe recibir una instancia de carta distinta; no pueden compartir el mismo objeto de rol");
    }

}