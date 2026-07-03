package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.partida.ObservadorResultadoPartida;
import edu.fiuba.paradigmas.modelo.partida.ResultadoPartida;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class CondicionesDeVictoriaTest {

    @Test
    public void losCiudadanosGananCuandoNoQuedanMafiososVivos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        mafioso.morir();

        ObservadorResultadoPartida observador = mock(ObservadorResultadoPartida.class);
        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion(), observador);
        ResultadoPartida resultado = moderador.evaluarGanador();

        Fase fase = mock(Fase.class);

        resultado.ejecutar(fase, moderador);

        verify(observador, times(1)).anunciarVictoriaCiudadanos();
    }

    @Test
    public void laMafiaGanaCuandoIgualaEnNumeroALosCiudadanos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        ciudadano1.morir();

        ObservadorResultadoPartida observador = mock(ObservadorResultadoPartida.class);
        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion(), observador);
        ResultadoPartida resultado = moderador.evaluarGanador();

        Fase fase = mock(Fase.class);

        resultado.ejecutar(fase, moderador);

        verify(observador, times(1)).anunciarVictoriaMafia();
    }

    @Test
    public void siHayMasCiudadanosLaPartidaContinuaYAvanzaLaFase() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());

        ObservadorResultadoPartida observador = mock(ObservadorResultadoPartida.class);
        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion(), observador);
        ResultadoPartida resultado = moderador.evaluarGanador();

        Fase fase = mock(Fase.class);

        resultado.ejecutar(fase, moderador);

        verify(fase, times(1)).avanzar(any(Moderador.class));
        verify(observador, never()).anunciarVictoriaMafia();
        verify(observador, never()).anunciarVictoriaCiudadanos();
    }
}
