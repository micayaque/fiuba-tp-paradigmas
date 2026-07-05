package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.partida.ResultadoPartida;
import edu.fiuba.paradigmas.modelo.partida.VictoriaCiudadanos;
import edu.fiuba.paradigmas.modelo.partida.VictoriaMafia;
import edu.fiuba.paradigmas.modelo.partida.PartidaEnCurso;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotificacionVictoriaAlResolverFaseTest {

    @Test
    public void alResolverLaFaseSeRetornaVictoriaCiudadanosCuandoNoQuedanMafiososVivos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        mafioso.morir();

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());

        ResultadoPartida resultado = moderador.evaluarGanador();

        assertTrue(resultado instanceof VictoriaCiudadanos, "Debería resultar en victoria para los ciudadanos");
    }

    @Test
    public void alResolverLaFaseSeRetornaVictoriaMafiaCuandoIgualaEnNumeroALosCiudadanos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        ciudadano1.morir();

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2), new EmpateDiurnoSinEliminacion());

        ResultadoPartida resultado = moderador.evaluarGanador();

        assertTrue(resultado instanceof VictoriaMafia, "Debería resultar en victoria para la mafia");
    }

    @Test
    public void alResolverLaFaseNoSeRetornaVictoriaSiLaPartidaContinua() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        Jugador ciudadano3 = new Jugador("ciudadano3", new Ciudadano());

        Moderador moderador = new Moderador(List.of(mafioso, ciudadano1, ciudadano2,  ciudadano3), new EmpateDiurnoSinEliminacion());

        moderador.registrarVoto(mafioso, ciudadano1);
        moderador.resolverVotacion();

        ResultadoPartida resultado = moderador.evaluarGanador();

        assertTrue(resultado instanceof PartidaEnCurso, "La partida debería continuar");
    }
}