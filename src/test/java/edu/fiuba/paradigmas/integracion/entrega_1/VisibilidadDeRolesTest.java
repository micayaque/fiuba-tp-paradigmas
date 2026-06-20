package edu.fiuba.paradigmas.integracion.entrega_1;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VisibilidadDeRolesTest {

    private List<String> nombres() {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            nombres.add("Jugador " + i);
        }
        return nombres;
    }

    @Test
    public void unJugadorPuedeVerSuPropioRol() {
        List<Rol> roles = List.of(new Mafioso(), new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano());
        List<Jugador> jugadores = new CreadorDeJugadores().crearPartida(nombres(), roles);

        Jugador jugador = jugadores.get(0);
        List<Jugador> conocidos = new ArrayList<>();

        jugador.puedeConocerElRolDe(jugador, conocidos);

        assertTrue(conocidos.contains(jugador));
        assertEquals(1, conocidos.size());
    }

    @Test
    public void unJugadorNoMafiosoNoPuedeVerElRolDeLosDemasDuranteLaPartida() {
        List<Rol> roles = List.of(new Mafioso(), new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano());
        List<Jugador> jugadores = new CreadorDeJugadores().crearPartida(nombres(), roles);

        Jugador noMafioso = new Jugador("no mafioso", new Ciudadano());

        for(Jugador j : jugadores){
            List<Jugador> conocidos = new ArrayList<>();
            noMafioso.puedeConocerElRolDe(j, conocidos);

            assertFalse(conocidos.contains(j));
            assertEquals(0, conocidos.size());
        }
    }

}