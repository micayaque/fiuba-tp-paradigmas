package edu.fiuba.paradigmas.unitarios.fasenocturna;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.EmpateNocturnoMafia;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.resultadoVotacion.JugadorElegido;
import edu.fiuba.paradigmas.modelo.voto.Voto;
import edu.fiuba.paradigmas.modelo.voto.VotoDelPadrino;
import edu.fiuba.paradigmas.modelo.resultadoVotacion.Empate;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ResultadoVotacionTest {

    @Test
    public void victimaElegidaSiempreAplicaLaMuerteAlJugador() {
        Fase fase = mock(Fase.class);
        Jugador jugador = mock(Jugador.class);
        JugadorElegido victima = new JugadorElegido(jugador);

        AccionVotacion accion = victima.resolver();

        accion.ejecutar(fase);

        verify(jugador, times(1)).morir();
    }

    @Test
    public void empateAplicaNochePacificaSiNadieTienePoderDeDesempate() {
        Fase  fase = mock(Fase.class);
        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);

        Voto votoJugador1 = new Voto(jugador1, 2);
        Voto votoJugador2 = new Voto(jugador2, 2);

        Empate empate = new Empate(List.of(votoJugador1, votoJugador2), List.of(jugador1, jugador2), new EmpateDiurnoSinEliminacion());
        AccionVotacion accion = empate.resolver();
        accion.ejecutar(fase);

        verify(jugador1, never()).morir();
        verify(jugador2, never()).morir();
    }

    @Test
    public void empateAplicaMuerteSiElPadrinoParticipoEnElEmpate() {
        Fase  fase = mock(Fase.class);
        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);

        Voto votoJugador1 = new Voto(jugador1, 2);
        Voto votoJugador2 = new VotoDelPadrino(jugador2);
        Empate empate = new Empate(List.of(votoJugador1, votoJugador2), List.of(jugador1, jugador2), new EmpateNocturnoMafia());

        AccionVotacion accion = empate.resolver();
        accion.ejecutar(fase);

        verify(jugador2, times(1)).morir();
        verify(jugador1, never()).morir();
    }
}