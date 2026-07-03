package edu.fiuba.paradigmas.integracion.entrega_1;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;

import java.util.*;

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

        List<Jugador> jugadores = new CreadorDeJugadores(new Random()).crearPartida(nombres(), roles);

        assertEquals(7, jugadores.size());

        Random random = new Random();
        CreadorDeJugadores repartida = new CreadorDeJugadores(random);
        jugadores.forEach(jugador -> jugador.contarseEn(repartida));

        assertEquals(2, repartida.cantidadDeMafiosos());
        assertEquals(1, repartida.cantidadDeDetectives());
        assertEquals(1, repartida.cantidadDeMedicos());
        assertEquals(3, repartida.cantidadDeCiudadanos());
    }

    @Test
    public void distintasPartidasGeneranOrdenesDiferentesDeRoles() {
        List<Rol> roles = List.of(
                new Mafioso(), new Mafioso(), new Detective(), new Medico(),
                new Ciudadano(), new Ciudadano(), new Ciudadano()
        );
        List<String> nombres = nombres();
        
        List<Long> semillasDePartida = List.of(123L, 456L, 789L);
        
        Set<List<String>> ordenesVistos = new HashSet<>();

        for (Long semilla : semillasDePartida) {
            Random randomInyectado = new Random(semilla);
            CreadorDeJugadores creador = new CreadorDeJugadores(randomInyectado);
            
            List<Jugador> jugadores = creador.crearPartida(nombres, roles);
            List<String> ordenDeEstaPartida = new ArrayList<>();
            for (Jugador j : jugadores) {
                Random random = new Random();
                CreadorDeJugadores identificador = new CreadorDeJugadores(random);
                j.contarseEn(identificador);
                if (identificador.cantidadDeMafiosos() == 1) {
                    ordenDeEstaPartida.add("Mafioso");
                } else if (identificador.cantidadDeDetectives() == 1) {
                    ordenDeEstaPartida.add("Detective");
                } else if (identificador.cantidadDeMedicos() == 1) {
                    ordenDeEstaPartida.add("Medico");
                } else if (identificador.cantidadDeCiudadanos() == 1) {
                    ordenDeEstaPartida.add("Ciudadano");
                } else if (identificador.cantidadDePadrinos() == 1) {
                    ordenDeEstaPartida.add("Padrino");
                } else if (identificador.cantidadDeSheriffs() == 1) {
                    ordenDeEstaPartida.add("Sheriff");
                }
            }
            ordenesVistos.add(ordenDeEstaPartida);
        }

        assertEquals(3, ordenesVistos.size(), 
            "Cada partida con una semilla distinta debería generar un mazo único");
    }
}
