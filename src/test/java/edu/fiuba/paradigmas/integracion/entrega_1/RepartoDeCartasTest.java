package edu.fiuba.paradigmas.integracion.entrega_1;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.mazo.ContadorDeRoles;
import edu.fiuba.paradigmas.modelo.mazo.Mazo;
import edu.fiuba.paradigmas.modelo.reparto.Repartidor;
import edu.fiuba.paradigmas.modelo.rol.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepartoDeCartasTest {

    private List<String> nombres(int cantidad) {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            nombres.add("Jugador " + i);
        }
        return nombres;
    }

    @Test
    public void cadaJugadorRecibeUnaCartaYSeRespetaLaComposicion() {
        List<Rol> roles = new Mazo().generarPara(new ArrayList<>(List.of(
                new Mafioso(), new Mafioso(), new Detective(), new Medico(),
                new Ciudadano(), new Ciudadano(), new Ciudadano())));

        List<Jugador> jugadores = new Repartidor().repartir(nombres(7), roles);

        assertEquals(7, jugadores.size());

        ContadorDeRoles repartida = new ContadorDeRoles();
        for (Jugador jugador : jugadores) {
            jugador.contarseEn(repartida);
        }

        assertEquals(2, repartida.cantidadDeMafiosos());
        assertEquals(1, repartida.cantidadDeDetectives());
        assertEquals(1, repartida.cantidadDeMedicos());
        assertEquals(3, repartida.cantidadDeCiudadanos());
    }

    @Test
    public void elOrdenDeCreacionDelMazoDeRolesEsAleatorioEntrePartidas() {
        List<Rol> roles = new ArrayList<>(List.of(
                new Mafioso(), new Mafioso(), new Detective(), new Medico(),
                new Ciudadano(), new Ciudadano(), new Ciudadano()));
        Mazo mazo = new Mazo();

        Set<List<Rol>> asignacionesDeRolesVistas = new HashSet<>();

        for (int intento = 0; intento < 50; intento++) {
            List<Rol> rolesAsignados = mazo.generarPara(roles);
            asignacionesDeRolesVistas.add(rolesAsignados);
        }

        assertTrue(asignacionesDeRolesVistas.size() > 1,
                "El reparto deberia asignar distintos roles a un mismo jugador entre partidas");
    }
}
