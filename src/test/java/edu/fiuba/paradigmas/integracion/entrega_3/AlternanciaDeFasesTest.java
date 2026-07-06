package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fase.JugadorEliminado;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AlternanciaDeFasesTest {

    @Test
    public void laPartidaAlternaEntreNocheYDiaIncrementandoLaRondaAlVolverALaNoche() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        Jugador ciudadano3 = new Jugador("ciudadano3", new Ciudadano());
        Jugador ciudadano4 = new Jugador("ciudadano4", new Ciudadano());
        List<Jugador> jugadores = List.of(mafioso, ciudadano1, ciudadano2, ciudadano3, ciudadano4);

        Moderador moderador = new Moderador(jugadores, new EmpateDiurnoSinEliminacion());
        assertEquals(0, moderador.numeroDeRonda());
        moderador.comenzarFaseNocturna();
        assertEquals(1, moderador.numeroDeRonda());

        moderador.registrarVoto(mafioso, ciudadano1);
        ResultadoFase resultadoNoche = moderador.resolverVotacion();

        assertTrue(resultadoNoche instanceof JugadorEliminado, "El comportamiento nocturno debería ejecutarse con éxito");
        moderador.comenzarFaseDiurna();

        assertEquals(1, moderador.numeroDeRonda(), "La ronda debe mantenerse en 0 durante el día");

        moderador.registrarVoto(ciudadano3, ciudadano2);
        moderador.registrarVoto(ciudadano4, ciudadano2);
        ResultadoFase resultadoDia = moderador.resolverVotacion();

        assertTrue(resultadoDia instanceof JugadorEliminado, "La eliminación diurne debería ejecutarse con éxito");

        moderador.comenzarFaseNocturna();

        assertEquals(2, moderador.numeroDeRonda(), "Al volver a la noche se inicia la Ronda 1");
    }
}
