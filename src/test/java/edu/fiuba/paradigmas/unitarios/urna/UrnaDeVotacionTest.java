package edu.fiuba.paradigmas.unitarios.urna;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.voto.Voto;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.*;

public class UrnaDeVotacionTest {

    @Test
    public void devuelveAlJugadorConLaMayoriaDeVotos() {
        Fase fase = mock(Fase.class);
        Urna urnaVotacion = new Urna(new EmpateDiurnoSinEliminacion());

        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());

        urnaVotacion.agregarVoto(new Voto(ciudadano1));
        urnaVotacion.agregarVoto(new Voto(ciudadano1));
        urnaVotacion.agregarVoto(new Voto(ciudadano2));

        AccionFase resultadoVotacion = urnaVotacion.contarVotos();
        resultadoVotacion.ejecutar(fase);

        List<Jugador> vivos = new ArrayList<>();
        ciudadano1.estaVivo(vivos);
        assertTrue(vivos.isEmpty(), "ciudadano1 debería haber muerto por tener mayoría");

        ciudadano2.estaVivo(vivos);
        assertTrue(vivos.contains(ciudadano2), "ciudadano2 debería seguir vivo");
    }

    @Test
    public void unNuevoCandidatoMayorLimpiaLosEmpatesPrevios() {
        Fase  fase = mock(Fase.class);
        Urna urnaVotacion = new Urna(new EmpateDiurnoSinEliminacion());
        Jugador empatado1 = new Jugador("A", new Ciudadano());
        Jugador empatado2 = new Jugador("B", new Ciudadano());
        Jugador ganador = new Jugador("C", new Ciudadano());

        urnaVotacion.agregarVoto(new Voto(empatado1));
        urnaVotacion.agregarVoto(new Voto(empatado2));

        urnaVotacion.agregarVoto(new Voto(ganador));
        urnaVotacion.agregarVoto(new Voto(ganador));

        AccionFase resultadoVotacion = urnaVotacion.contarVotos();
        resultadoVotacion.ejecutar(fase);

        List<Jugador> vivos = new ArrayList<>();
        ganador.estaVivo(vivos);
        empatado1.estaVivo(vivos);
        empatado2.estaVivo(vivos);

        assertFalse(vivos.contains(ganador), "El nuevo ganador debió limpiar los empates previos y morir");
        assertTrue(vivos.contains(empatado1), "El jugador empatado 1 debió salvarse");
        assertTrue(vivos.contains(empatado2), "El jugador empatado 2 debió salvarse");
    }
}