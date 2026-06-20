package edu.fiuba.paradigmas.integracion.entrega_1;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.rol.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepartoDeCartasTest {

    private List<String> nombres() {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            nombres.add("Jugador " + i);
        }
        return nombres;
    }

    @Test
    public void cadaJugadorRecibeUnaCartaYSeRespetaLaComposicion() {
        List<Rol> roles = List.of(
                new Mafioso(), new Mafioso(), new Detective(), new Medico(),
                new Ciudadano(), new Ciudadano(), new Ciudadano());

        List<Jugador> jugadores = new CreadorDeJugadores().crearPartida(nombres(), roles);

        assertEquals(7, jugadores.size());

        ValidadorDeComposicionDelMazo repartida = new ValidadorDeComposicionDelMazo();
        jugadores.forEach(jugador -> jugador.contarseEn(repartida));

        assertEquals(2, repartida.cantidadDeMafiosos());
        assertEquals(1, repartida.cantidadDeDetectives());
        assertEquals(1, repartida.cantidadDeMedicos());
        assertEquals(3, repartida.cantidadDeCiudadanos());
    }

    @Test
    public void elOrdenDeCreacionDelMazoDeRolesEsAleatorioEntrePartidas() {
        List<Rol> roles = List.of(new Mafioso(), new Mafioso(), new Detective(), new Medico(),
                                    new Ciudadano(), new Ciudadano(), new Ciudadano());
        List<String> nombres = nombres();
        CreadorDeJugadores creador = new CreadorDeJugadores();

        Set<List<String>> asignacionesDeRolesVistas = new HashSet<>();
        for (int intento = 0; intento < 50; intento++) {
            List<Jugador> jugadores = creador.crearPartida(nombres, roles);
            List<String> rolesDeEstaPartida = new ArrayList<>();
            for (Jugador j : jugadores) {
                ValidadorDeComposicionDelMazo identificador = new ValidadorDeComposicionDelMazo();
                j.contarseEn(identificador);
                if (identificador.cantidadDeMafiosos() == 1) {
                    rolesDeEstaPartida.add("Mafioso");
                } else if (identificador.cantidadDeDetectives() == 1) {
                    rolesDeEstaPartida.add("Detective");
                } else if (identificador.cantidadDeMedicos() == 1) {
                    rolesDeEstaPartida.add("Medico");
                } else if (identificador.cantidadDeCiudadanos() == 1) {
                    rolesDeEstaPartida.add("Ciudadano");
                } else if (identificador.cantidadDePadrinos() == 1) {
                    rolesDeEstaPartida.add("Padrino");
                } else if (identificador.cantidadDeSheriffs() == 1) {
                    rolesDeEstaPartida.add("Sheriff");
                }
            }
            asignacionesDeRolesVistas.add(rolesDeEstaPartida);
        }
        assertTrue(asignacionesDeRolesVistas.size() > 1,
                "El reparto deberia asignar distintos roles a un mismo jugador entre partidas");
    }
}
