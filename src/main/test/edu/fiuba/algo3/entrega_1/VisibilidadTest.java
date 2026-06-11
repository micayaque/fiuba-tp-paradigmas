package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

public class VisibilidadTest {

    private List<Jugador> jugadores;
    private List<Jugador> mafiosos;
    private List<Jugador> ciudadanos;
    private Jugador detective;

    @BeforeEach
    public void setUp() {
        this.jugadores = JugadorTestFactory.crearJugadores(12);
        this.mafiosos = jugadores.stream()
                                .filter(j -> j.rol().getClass().equals(Mafioso.class))
                                .collect(Collectors.toList());
        this.ciudadanos = jugadores.stream()
                                   .filter(j -> j.rol().getClass().equals(Ciudadano.class))
                                   .collect(Collectors.toList());
        this.detective = jugadores.stream()
                                  .filter(j -> j.rol().getClass().equals(Detective.class))
                                  .findFirst()
                                  .orElseThrow();
    }

    @Test
    public void unJugadorPuedeVerSuPropioRol() {

        Jugador jugador = jugadores.get(0);

        assertTrue(jugador.conoceElRolDe(jugador, jugador.rol()));
    }

    @Test
    public void unJugadorNoConoceElRolDeLosDemas() {

        Jugador ciudadano = ciudadanos.get(0);

        assertFalse(ciudadano.conoceElRolDe(detective, detective.rol()));
        assertFalse(detective.conoceElRolDe(ciudadano, ciudadano.rol()));
    }

    @Test
    public void losMafiososConocenLaIdentidadDeSusComplicesAlIniciarLaPartida() {

        Jugador mafioso1 = mafiosos.get(0);
        Jugador mafioso2 = mafiosos.get(1);

        assertTrue(mafioso1.conoceElRolDe(mafioso2, mafioso2.rol()));
        assertTrue(mafioso2.conoceElRolDe(mafioso1, mafioso1.rol()));
    }

    @Test
    public void losMafiososNoConocenElRolDeLosCiudadanos() {

        Jugador mafioso = mafiosos.get(0);
        Jugador ciudadano = ciudadanos.get(0);

        assertFalse(mafioso.conoceElRolDe(ciudadano, ciudadano.rol()));
        assertFalse(ciudadano.conoceElRolDe(mafioso, mafioso.rol()));
    }

    @Test
    public void unCiudadanoNoConoceElRolDeNadieAunDespuesDeLaApertura() {

        Jugador mafioso = mafiosos.get(0);
        Jugador ciudadano1 = ciudadanos.get(0);
        Jugador ciudadano2 = ciudadanos.get(1);

        assertFalse(ciudadano1.conoceElRolDe(ciudadano2, ciudadano2.rol()));
        assertFalse(ciudadano1.conoceElRolDe(mafioso, mafioso.rol()));
    }
}