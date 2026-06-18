package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.excepciones.CandidatoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.VotacionNoIniciadaExcepcion;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FaseDiurnaTest {

    @Test
    public void faseDiurnaDelegaLaNominacionAlJugador() {
        FaseDiurna fase = new FaseDiurna();
        fase.configurarEstrategiaEmpate(new EmpateDiurnoSinEliminacion());

        Jugador nominante = mock(Jugador.class);
        Jugador nominado = mock(Jugador.class);

        fase.recibirNominacion(nominante, nominado);

        verify(nominante).votarA(eq(nominado), any(Urna.class));
    }

    @Test
    public void lanzaExcepcionSiSeIntentaVotarAntesDeIniciarVotacion() {
        FaseDiurna fase = new FaseDiurna();
        Jugador votante = new Jugador("Votante", new Ciudadano());
        Jugador votado = new Jugador("Votado", new Ciudadano());

        assertThrows(VotacionNoIniciadaExcepcion.class,
                () -> fase.recibirVoto(votante, votado),
                "El sistema debe impedir votos antes de que se cierre la nominación"
        );
    }

    @Test
    public void recibeVotoEsExitosoSiElJugadorFuePreviamenteNominado() {
        FaseDiurna fase = new FaseDiurna();
        fase.configurarEstrategiaEmpate(new EmpateDiurnoSinEliminacion());

        Jugador nominante = new Jugador("Nominante", new Ciudadano());
        Jugador nominado = new Jugador("Nominado", new Ciudadano());
        Jugador votante = new Jugador("Votante", new Ciudadano());

        fase.recibirNominacion(nominante, nominado);

        List<Jugador> lista = fase.iniciarVotacion();

        assertTrue(lista.contains(nominado), "La lista devuelta debe incluir al nominado");
        assertDoesNotThrow(
                () -> fase.recibirVoto(votante, nominado),
                "El sistema debe aceptar el voto hacia un jugador válidamente nominado"
        );
    }

    @Test
    public void lanzaExcepcionSiSeIntentaVotarAAlguienQueNoFueNominado() {
        FaseDiurna fase = new FaseDiurna();
        fase.configurarEstrategiaEmpate(new EmpateDiurnoSinEliminacion());

        Jugador nominante = new Jugador("Nominante", new Ciudadano());
        Jugador nominado = new Jugador("Nominado", new Ciudadano());
        Jugador fantasma = new Jugador("Fantasma", new Ciudadano());
        Jugador votante = new Jugador("Votante", new Ciudadano());

        fase.recibirNominacion(nominante, nominado);
        fase.iniciarVotacion();

        assertThrows(CandidatoInvalidoExcepcion.class,
                () -> fase.recibirVoto(votante, fantasma),
                "El sistema debe rechazar votos hacia jugadores que no pasaron la etapa de nominación"
        );
    }
}
