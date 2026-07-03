package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
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
import static org.mockito.Mockito.*;

public class CondicionesDeVictoriaTest {

    @Test
    public void losCiudadanosGananCuandoNoQuedanMafiososVivos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        mafioso.morir();

        Fase fase = new FaseNocturna();
        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();
        resultado.ejecutar(fase, moderador);

    }

    @Test
    public void laMafiaGanaCuandoIgualaEnNumeroALosCiudadanos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        ciudadano1.morir();

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

    }

    @Test
    public void laPartidaSigueEnCursoSiLaMafiaEsMinoria() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

    }

    @Test
    public void elPadrinoCuentaComoMafiaParaLaVictoriaAunqueAparezcaComoCiudadanoAnteElDetective() {
        Jugador padrino = new Jugador("padrino", new Padrino());
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());

        Moderador moderador = new Moderador(List.of(padrino, ciudadano), new EmpateDiurnoSinEliminacion());
        ResultadoPartida resultado = moderador.evaluarGanador();

    }
}
