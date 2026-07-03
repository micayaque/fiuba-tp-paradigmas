package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.partida.PartidaEnCurso;
import edu.fiuba.paradigmas.modelo.partida.ObservadorResultadoPartida;
import edu.fiuba.paradigmas.modelo.partida.ResultadoPartida;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificacionVictoriaAlResolverFaseTest {

    @Test
    public void alResolverLaFaseSeAnunciaLaVictoriaDeLosCiudadanosCuandoNoQuedanMafiososVivos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        mafioso.morir();

        ObservadorResultadoPartida observador = mock(ObservadorResultadoPartida.class);
        Moderador moderador = new Moderador(
                List.of(mafioso, ciudadano1, ciudadano2),
                new EmpateDiurnoSinEliminacion(),
                observador
        );

        ResultadoPartida resultado = moderador.evaluarGanador();
        resultado.ejecutar(mock(Fase.class), moderador);

        verify(observador, times(1)).anunciarVictoriaCiudadanos();
        verify(observador, times(0)).anunciarVictoriaMafia();
    }

    @Test
    public void alResolverLaFaseSeAnunciaLaVictoriaDeLaMafiaCuandoIgualaEnNumeroALosCiudadanos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        ciudadano1.morir();

        ObservadorResultadoPartida observador = mock(ObservadorResultadoPartida.class);
        Moderador moderador = new Moderador(
                List.of(mafioso, ciudadano1, ciudadano2),
                new EmpateDiurnoSinEliminacion(),
                observador
        );

        ResultadoPartida resultado = moderador.evaluarGanador();
        resultado.ejecutar(mock(Fase.class), moderador);

        verify(observador, times(1)).anunciarVictoriaMafia();
        verify(observador, times(0)).anunciarVictoriaCiudadanos();
    }

    @Test
    public void alResolverLaFaseNoSeAnunciaVictoriaSiLaPartidaContinua() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        Jugador ciudadano3 = new Jugador("ciudadano3", new Ciudadano());

        ObservadorResultadoPartida observador = mock(ObservadorResultadoPartida.class);
        Moderador moderador = new Moderador(
                List.of(mafioso, ciudadano1, ciudadano2,  ciudadano3),
                new EmpateDiurnoSinEliminacion(),
                observador
        );

        moderador.registrarVoto(mafioso, ciudadano1);

        verifyNoInteractions(observador);
    }
}
