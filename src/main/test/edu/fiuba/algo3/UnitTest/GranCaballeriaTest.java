package edu.fiuba.algo3.UnitTest;

import edu.fiuba.algo3.modelo.Cartas.GranCaballeria;
import edu.fiuba.algo3.modelo.Color;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.PuntajeDeVictoria;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GranCaballeriaTest {
    GranCaballeria gc = new GranCaballeria();
    private void jugada(GranCaballeria gc, Jugador jugador) {
        jugador.sumarCaballero();
        gc.registrarCaballeroJugado(jugador);
    }

    @Test
    public void test01NadieTieneGranCaballeriaConMenosDeTresCaballeros() {
        GranCaballeria gc = new GranCaballeria();
        Jugador j1 = new Jugador("A", new Color("Azul"));

        gc.registrarCaballeroJugado(j1);
        gc.registrarCaballeroJugado(j1);

        PuntajeDeVictoria puntosEsperados = new PuntajeDeVictoria();

        assertTrue( j1.mismoPuntaje(puntosEsperados));
    }

    @Test
    public void test02PrimerJugadorEnAlcanzarTresCaballerosGanaBonus() {
        GranCaballeria gc = new GranCaballeria();
        Jugador j1 = new Jugador("A", new Color("Azul"));

        jugada(gc, j1);
        jugada(gc, j1);
        jugada(gc, j1);

        PuntajeDeVictoria puntosEsperados = new PuntajeDeVictoria();
        puntosEsperados.agregarPuntos(2);

        assertTrue(j1.mismoPuntaje(puntosEsperados));
    }

    @Test
    public void test03_SegundoJugadorNoObtieneBonusSiEmpata() {
        GranCaballeria gc = new GranCaballeria();
        Jugador j1 = new Jugador("A", new Color("Azul"));
        Jugador j2 = new Jugador("R", new Color("Rojo"));

        // j1 llega primero
        jugada(gc, j1);
        jugada(gc, j1);
        jugada(gc, j1);

        // j2 empata pero no supera
        jugada(gc, j2);
        jugada(gc, j2);
        jugada(gc, j2);

        PuntajeDeVictoria puntosEsperados = new PuntajeDeVictoria();
        PuntajeDeVictoria puntosEsperadosDerrota = new PuntajeDeVictoria();

        puntosEsperados.agregarPuntos(2);

        assertTrue(j1.mismoPuntaje(puntosEsperados));       // Mantiene
        assertTrue(j2.mismoPuntaje(puntosEsperadosDerrota)); // No roba
    }

    @Test
    public void test04_SegundoJugadorSuperaYRecibeElBonus() {
        GranCaballeria gc = new GranCaballeria();
        Jugador j1 = new Jugador("A", new Color("Azul"));
        Jugador j2 = new Jugador("R", new Color("Rojo"));

        // j1 llega a 3 primero
        jugada(gc, j1);
        jugada(gc, j1);
        jugada(gc, j1);

        // j2 juega 4 caballeros > supera
        jugada(gc, j2);
        jugada(gc, j2);
        jugada(gc, j2);
        jugada(gc, j2);

        PuntajeDeVictoria puntosEsperados = new PuntajeDeVictoria();
        PuntajeDeVictoria puntosEsperadosDerrota = new PuntajeDeVictoria();

        puntosEsperados.agregarPuntos(2);

        assertTrue(j1.mismoPuntaje(puntosEsperadosDerrota)); // Pierde
        assertTrue(j2.mismoPuntaje(puntosEsperados));        // Gana
    }

}
