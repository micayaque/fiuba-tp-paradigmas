package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.mazo.Mazo;
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

    private List<String> nombres(int cantidad) {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            nombres.add("Jugador " + i);
        }
        return nombres;
    }

    private Map<Class<?>, Long> clasificarPorTipo(List<Rol> roles) {
        return roles.stream().collect(Collectors.groupingBy(Rol::getClass, Collectors.counting()));
    }

    @Test
    public void cadaJugadorRecibeExactamenteUnRolDesdeSuCreacion() {
        List<Jugador> jugadores = new Mazo().repartir(nombres(7));

        assertEquals(7, jugadores.size());
        for (Jugador jugador : jugadores) {
            assertNotNull(jugador.miRol());
        }
    }

    @Test
    public void seRepartenTodasLasCartasDelMazoSinPerdidasNiDuplicados() {
        Mazo mazo = new Mazo();

        Map<Class<?>, Long> composicionEsperada = clasificarPorTipo(mazo.generarPara(7));
        List<Jugador> jugadores = mazo.repartir(nombres(7));

        Map<Class<?>, Long> composicionRepartida = jugadores.stream()
                .collect(Collectors.groupingBy(jugador -> jugador.miRol().getClass(), Collectors.counting()));

        assertEquals(composicionEsperada, composicionRepartida);
    }

    @Test
    public void elRepartoEsAleatorioEntrePartidas() {
        Set<Class<?>> rolesVistosEnLaPrimeraPosicion = new HashSet<>();

        for (int intento = 0; intento < 50; intento++) {
            List<Jugador> jugadores = new Mazo().repartir(nombres(7));
            rolesVistosEnLaPrimeraPosicion.add(jugadores.get(0).miRol().getClass());
        }

        assertTrue(rolesVistosEnLaPrimeraPosicion.size() > 1,
                "El reparto deberia asignar distintos roles a un mismo jugador entre partidas");
    }
}
