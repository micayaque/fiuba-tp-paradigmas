package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.*;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CondicionesDeVictoriaTest {

    @Test
    public void losCiudadanosGananCuandoNoQuedanMafiososVivos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        mafioso.morir();

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

        Fase fase = mock(Fase.class);

        resultado.ejecutar(fase, moderador);

        assertTrue(resultado instanceof VictoriaCiudadanos, "El resultado debería ser VictoriaCiudadanos");
    }

    @Test
    public void laMafiaGanaCuandoIgualaEnNumeroALosCiudadanos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        ciudadano1.morir();

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

        Fase fase = mock(Fase.class);

        resultado.ejecutar(fase, moderador);

        assertTrue(resultado instanceof VictoriaMafia, "El resultado debería ser VictoriaMafia");
    }

    @Test
    public void siHayMasCiudadanosLaPartidaContinuaYAvanzaLaFase() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

        Fase fase = mock(Fase.class);

        resultado.ejecutar(fase, moderador);

        assertTrue(resultado instanceof PartidaEnCurso, "El resultado debería ser PartidaEnCurso");
    }
}
