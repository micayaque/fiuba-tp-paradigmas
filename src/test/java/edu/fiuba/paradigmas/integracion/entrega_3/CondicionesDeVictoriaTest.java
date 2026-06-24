package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.partida.ResultadoPartida;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Padrino;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CondicionesDeVictoriaTest {

    @Test
    public void losCiudadanosGananCuandoNoQuedanMafiososVivos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        mafioso.morir();

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

        assertTrue(resultado.partidaTerminada());
        assertEquals("Ganan los Ciudadanos", resultado.anuncio());
    }

    @Test
    public void laMafiaGanaCuandoIgualaEnNumeroALosCiudadanos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        ciudadano1.morir();

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

        assertTrue(resultado.partidaTerminada());
        assertEquals("Gana la Mafia", resultado.anuncio());
    }

    @Test
    public void laPartidaSigueEnCursoSiLaMafiaEsMinoria() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

        assertFalse(resultado.partidaTerminada());
        assertEquals("La partida continúa", resultado.anuncio());
    }

    @Test
    public void elPadrinoCuentaComoMafiaParaLaVictoriaAunqueAparezcaComoCiudadanoAnteElDetective() {
        Jugador padrino = new Jugador("padrino", new Padrino());
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());

        Moderador moderador = new Moderador(List.of(padrino, ciudadano), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

        assertTrue(resultado.partidaTerminada());
        assertEquals("Gana la Mafia", resultado.anuncio(),
                "El Padrino debe contar como Mafia (su bando real), no como el 'Ciudadano' que finge ante el Detective");
    }
}
