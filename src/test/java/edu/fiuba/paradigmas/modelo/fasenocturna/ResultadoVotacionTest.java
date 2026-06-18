package edu.fiuba.paradigmas.modelo.fasenocturna;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.EmpateNocturnoMafia;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.urna.JugadorElegido;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.urna.VotoDelPadrino;
import edu.fiuba.paradigmas.modelo.urna.Empate;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ResultadoVotacionTest {

    @Test
    public void victimaElegidaSiempreAplicaLaMuerteAlJugador() {
        Jugador jugador = mock(Jugador.class);
        JugadorElegido victima = new JugadorElegido(jugador);

        AccionVotacion accion = victima.resolver();

        accion.ejecutar();

        verify(jugador, times(1)).morir();
    }

    @Test
    public void empateAplicaNochePacificaSiNadieTienePoderDeDesempate() {
        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);

        Voto votoJugador1 = new Voto(jugador1, 2);
        Voto votoJugador2 = new Voto(jugador2, 2);

        Empate empate = new Empate(List.of(votoJugador1, votoJugador2), List.of(jugador1, jugador2), new EmpateDiurnoSinEliminacion());
        AccionVotacion accion = empate.resolver();
        accion.ejecutar();

        verify(jugador1, never()).morir();
        verify(jugador2, never()).morir();
    }

    @Test
    public void empateAplicaMuerteSiElPadrinoParticipoEnElEmpate() {
        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);

        Voto votoJugador1 = new Voto(jugador1, 2);
        Voto votoJugador2 = new VotoDelPadrino(jugador2);
        Empate empate = new Empate(List.of(votoJugador1, votoJugador2), List.of(jugador1, jugador2), new EmpateNocturnoMafia());

        AccionVotacion accion = empate.resolver();
        accion.ejecutar();

        verify(jugador2, times(1)).morir();
        verify(jugador1, never()).morir();
    }
}