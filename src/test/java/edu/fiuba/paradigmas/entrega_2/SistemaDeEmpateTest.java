package edu.fiuba.paradigmas.entrega_2;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.empate.SistemaDeEmpate;
import edu.fiuba.paradigmas.modelo.excepciones.CandidatoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fasediurna.*;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SistemaDeEmpateTest {

    @Test
    public void empateConRondaSinEliminacionNadieMuere() {
        FaseDiurna fase = new FaseDiurna();
        fase.configurarEstrategiaEmpate(new EmpateDiurnoSinEliminacion());

        Jugador a = new Jugador("A", new Ciudadano());
        Jugador b = new Jugador("B", new Ciudadano());
        Jugador v1 = new Jugador("V1", new Ciudadano());
        Jugador v2 = new Jugador("V2", new Ciudadano());

        fase.recibirVoto(v1, a);
        fase.recibirVoto(v2, b);

        AccionVotacion accion = fase.ejecutarResultadoVotacion();
        accion.ejecutar();

        assertDoesNotThrow(() -> a.votarA(b, new Urna()),
                "El jugador A debería seguir vivo tras una ronda sin eliminación");
        assertDoesNotThrow(() -> b.votarA(a, new Urna()),
                "El jugador B debería seguir vivo tras una ronda sin eliminación");
    }

    @Test
    public void empateConBallotageRestringeCandidatosYAlguienMuereEnSegundaVuelta() {
        FaseDiurna fase = new FaseDiurna();
        SistemaDeEmpate ballotage = new EmpateDiurnoBallotage(fase);
        fase.configurarEstrategiaEmpate(ballotage);

        Jugador a = new Jugador("A", new Ciudadano());
        Jugador b = new Jugador("B", new Ciudadano());
        Jugador c = new Jugador("C", new Ciudadano());

        Jugador v1 = new Jugador("V1", new Ciudadano());
        Jugador v2 = new Jugador("V2", new Ciudadano());

        fase.recibirVoto(v1, a);
        fase.recibirVoto(v2, b);

        AccionVotacion accionPrimeraVuelta = fase.ejecutarResultadoVotacion();
        accionPrimeraVuelta.ejecutar();

        assertThrows(CandidatoInvalidoExcepcion.class,
                () -> fase.recibirVoto(v1, c),
                "El sistema debe rechazar votos hacia jugadores que no están en el ballotage");

        fase.recibirVoto(v1, a);
        fase.recibirVoto(v2, a);

        AccionVotacion accionSegundaVuelta = fase.ejecutarResultadoVotacion();
        accionSegundaVuelta.ejecutar();

        assertThrows(JugadorMuertoExcepcion.class,
                () -> a.votarA(b, new Urna()),
                "El jugador A debió ser eliminado tras perder el ballotage");

        assertDoesNotThrow(() -> b.votarA(c, new Urna()),
                "El jugador B debe seguir vivo tras salvarse en el ballotage");
    }
}