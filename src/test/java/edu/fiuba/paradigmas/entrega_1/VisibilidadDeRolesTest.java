package edu.fiuba.paradigmas.entrega_1;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.mazo.Mazo;
import edu.fiuba.paradigmas.modelo.reparto.Repartidor;
import edu.fiuba.paradigmas.modelo.rol.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VisibilidadDeRolesTest {

    private List<String> nombres(int cantidad) {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) nombres.add("Jugador " + i);
        return nombres;
    }

    @Test
    public void unJugadorPuedeVerSuPropioRol() {
        List<Rol> roles = new Mazo().validar(List.of( new Mafioso(), new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano()));
        List<Jugador> jugadores = new Repartidor().repartir(nombres(5), roles);

        Jugador jugador = jugadores.get(0);
        List<Jugador> conocidos = new ArrayList<>();
        jugador.puedeConocerElRolDe(jugador, conocidos);

        assertTrue(conocidos.contains(jugador));
        assertEquals(1, conocidos.size());
    }

    @Test
    public void unJugadorNoMafiosoNoPuedeVerElRolDeLosDemasDuranteLaPartida() {
        List<Rol> roles = new Mazo().validar(List.of( new Mafioso(), new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano()));
        List<Jugador> jugadores = new Repartidor().repartir(nombres(5), roles);
        Jugador noMafioso = new Jugador("no mafioso", new Ciudadano());

        for(Jugador j :  jugadores){
            List<Jugador> conocidos = new ArrayList<>();
            noMafioso.puedeConocerElRolDe(j, conocidos);
            assertFalse(conocidos.contains(j));
            assertEquals(0, conocidos.size());
        }
    }

}