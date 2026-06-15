package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.excepciones.EmpateMafiosoExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.PadrinoImpostorExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Padrino;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FaseNocturnaTest {

    @Test
    public void recibirVotoYEjecutarResultadoMataAlJugadorMasVotadoYLoDevuelve() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador victima = new Jugador("victima", new Ciudadano());

        FaseNocturna fase = new FaseNocturna(List.of(mafioso, victima));

        fase.recibirVoto(mafioso, victima);
        ResultadoFase resultado = fase.ejecutarResultadoVotacion();

        assertEquals(victima, resultado.jugadorElegidoPorMafia(), "El resultado debe contener a la víctima");

        List<Jugador> vivos = new ArrayList<>();
        victima.estaVivo(vivos);
        assertTrue(vivos.isEmpty(), "La víctima elegida debió haber muerto realmente");
    }

    @Test
    public void ejecutarDesempateMataALaVictimaYDevuelveElResultado() {
        Jugador padrino = new Jugador("padrino", new Padrino());
        Jugador eleccionDelPadrino = new Jugador("victima en empate", new Ciudadano());

        FaseNocturna fase = new FaseNocturna(List.of(padrino, eleccionDelPadrino));
        ResultadoFase resultado = fase.ejecutarDesempate(padrino, eleccionDelPadrino);

        assertEquals(eleccionDelPadrino, resultado.jugadorElegidoPorMafia());

        List<Jugador> vivos = new ArrayList<>();
        eleccionDelPadrino.estaVivo(vivos);
        assertTrue(vivos.isEmpty(), "La víctima elegida por el padrino debió haber muerto");
    }

    @Test
    public void siHayEmpateEnLaVotacionLaUrnaLanzaEmpateMafiosoExcepcion() {
        Jugador mafioso1 = new Jugador("mafioso1", new Mafioso());
        Jugador mafioso2 = new Jugador("mafioso2", new Mafioso());

        Jugador victima1 = new Jugador("victima1", new Ciudadano());
        Jugador victima2 = new Jugador("victima2", new Ciudadano());

        FaseNocturna fase = new FaseNocturna(List.of(mafioso1, mafioso2, victima1, victima2));

        fase.recibirVoto(mafioso1, victima1);
        fase.recibirVoto(mafioso2, victima2);

        EmpateMafiosoExcepcion excepcion = assertThrows(EmpateMafiosoExcepcion.class, fase::ejecutarResultadoVotacion);

        assertTrue(excepcion.candidatosEmpatados().contains(victima1));
        assertTrue(excepcion.candidatosEmpatados().contains(victima2));
        assertEquals(2, excepcion.candidatosEmpatados().size());
    }

    @Test
    public void ejecutarDesempateDevuelveElResultadoCorrecto() {
        Jugador padrino = new Jugador("padrino", new Padrino());
        Jugador eleccionDelPadrino = new Jugador("victima en empate", new Ciudadano());

        FaseNocturna fase = new FaseNocturna(List.of(padrino, eleccionDelPadrino));

        ResultadoFase resultado = fase.ejecutarDesempate(padrino, eleccionDelPadrino);

        assertEquals(eleccionDelPadrino, resultado.jugadorElegidoPorMafia(), "El resultado debe ser la víctima elegida por el padrino");
    }

    @Test
    public void unPadrinoMuertoIntentaDesempatarLanzaJugadorMuertoExcepcion() {
        Jugador padrinoMuerto = new Jugador("padrino", new Padrino());
        Jugador victima = new Jugador("victima", new Ciudadano());

        FaseNocturna fase = new FaseNocturna(List.of(padrinoMuerto, victima));
        padrinoMuerto.morir();

        assertThrows(JugadorMuertoExcepcion.class, () -> fase.ejecutarDesempate(padrinoMuerto, victima)
        , "Debería lanzar JugadorMuertoExcepcion si el padrino ya está muerto");
    }

    @Test
    public void unMafiosoQueNoEsPadrinoIntentaDesempatarLanzaPadrinoImpostorExcepcion() {
        Jugador padrinoImpostor = new Jugador("mafioso", new Mafioso());
        Jugador victima = new Jugador("victima", new Ciudadano());

        FaseNocturna fase = new FaseNocturna(List.of(padrinoImpostor, victima));

        assertThrows(PadrinoImpostorExcepcion.class, () -> fase.ejecutarDesempate(padrinoImpostor, victima)
        , "Debería fallar si un jugador que no es padrino intenta desempatar");
    }
}
