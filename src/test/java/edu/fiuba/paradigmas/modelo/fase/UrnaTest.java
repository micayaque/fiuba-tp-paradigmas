package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.fase.urna.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.fase.urna.Voto;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UrnaTest {

    @Test
    public void devuelveAlJugadorConLaMayoriaDeVotos() {
        Urna urna = new Urna();

        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());

        urna.agregarVoto(new Voto(ciudadano1));
        urna.agregarVoto(new Voto(ciudadano1));
        urna.agregarVoto(new Voto(ciudadano2));

        ResultadoVotacion resultadoVotacion = urna.contarVotos();
        resultadoVotacion.resolver().ejecutar();

        List<Jugador> vivos = new ArrayList<>();
        ciudadano1.estaVivo(vivos);
        assertTrue(vivos.isEmpty(), "ciudadano1 debería haber muerto por tener mayoría");

        ciudadano2.estaVivo(vivos);
        assertTrue(vivos.contains(ciudadano2), "ciudadano2 debería seguir vivo");
    }

    @Test
    public void unNuevoCandidatoMayorLimpiaLosEmpatesPrevios() {
        Urna urna = new Urna();
        Jugador empatado1 = new Jugador("A", new Ciudadano());
        Jugador empatado2 = new Jugador("B", new Ciudadano());
        Jugador ganador = new Jugador("C", new Ciudadano());

        urna.agregarVoto(new Voto(empatado1));
        urna.agregarVoto(new Voto(empatado2));

        urna.agregarVoto(new Voto(ganador));
        urna.agregarVoto(new Voto(ganador));

        ResultadoVotacion resultadoVotacion = urna.contarVotos();
        resultadoVotacion.resolver().ejecutar();

        List<Jugador> vivos = new ArrayList<>();
        ganador.estaVivo(vivos);
        empatado1.estaVivo(vivos);
        empatado2.estaVivo(vivos);

        assertFalse(vivos.contains(ganador), "El nuevo ganador debió limpiar los empates previos y morir");
        assertTrue(vivos.contains(empatado1), "El jugador empatado 1 debió salvarse");
        assertTrue(vivos.contains(empatado2), "El jugador empatado 2 debió salvarse");
    }
}