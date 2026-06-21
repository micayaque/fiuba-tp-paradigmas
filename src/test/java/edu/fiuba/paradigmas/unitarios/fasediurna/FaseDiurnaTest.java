package edu.fiuba.paradigmas.unitarios.fasediurna;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.fasediurna.FaseDiurna;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FaseDiurnaTest {

    @Test
    public void recibeVotoEsExitosoSiElJugadorFuePreviamenteNominado() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());

        Jugador nominante = new Jugador("Nominante", new Ciudadano());
        Jugador nominado = new Jugador("Nominado", new Ciudadano());
        Jugador votante = new Jugador("Votante", new Ciudadano());

        fase.recibirNominacion(nominante, nominado);

        List<Jugador> nominados = fase.iniciarVotacion();

        assertTrue(nominados.contains(nominado), "La lista devuelta debe incluir al nominado");
        assertDoesNotThrow(
                () -> fase.recibirVoto(votante, nominado),
                "El sistema debe aceptar el voto hacia un jugador válidamente nominado"
        );
    }

    @Test
    public void lanzaExcepcionSiSeIntentaVotarAAlguienQueNoFueNominado() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());

        Jugador nominante = new Jugador("Nominante", new Ciudadano());
        Jugador nominado = new Jugador("Nominado", new Ciudadano());
        Jugador fantasma = new Jugador("Fantasma", new Ciudadano());
        Jugador votante = new Jugador("Votante", new Ciudadano());

        fase.recibirNominacion(nominante, nominado);
        fase.iniciarVotacion();

        assertThrows(VotoInvalidoExcepcion.class,
                () -> fase.recibirVoto(votante, fantasma),
                "El sistema debe rechazar votos hacia jugadores que no pasaron la etapa de nominación"
        );
    }
}
