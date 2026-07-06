package edu.fiuba.paradigmas.integracion.entrega_2;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SistemaDeEmpateTest {

    @Test
    public void empateConRondaSinEliminacionNadieMuere() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());

        Jugador a = new Jugador("A", new Ciudadano());
        Jugador b = new Jugador("B", new Ciudadano());
        Jugador v1 = new Jugador("V1", new Ciudadano());
        Jugador v2 = new Jugador("V2", new Ciudadano());

        fase.recibirVoto(v1, a);
        fase.recibirVoto(v2, b);
        fase.recibirVoto(v1, a);
        fase.recibirVoto(v2, b);

        AccionVotacion accion = fase.ejecutarResultadoVotacion();
        accion.ejecutar(fase);

        assertDoesNotThrow(() -> a.votarComoCiudadano(v1, new Urna(new EmpateDiurnoSinEliminacion())),
                "El jugador A debería seguir vivo tras una ronda sin eliminación");
        assertDoesNotThrow(() -> b.votarComoCiudadano(v2,  new Urna(new EmpateDiurnoSinEliminacion())),
                "El jugador B debería seguir vivo tras una ronda sin eliminación");
    }

    @Test
    public void empateConBallotageRestringeCandidatosYAlguienMuereEnSegundaVuelta() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoBallotage());

        Jugador a = new Jugador("A", new Ciudadano());
        Jugador b = new Jugador("B", new Ciudadano());
        Jugador c = new Jugador("C", new Ciudadano());

        Jugador v1 = new Jugador("V1", new Ciudadano());
        Jugador v2 = new Jugador("V2", new Ciudadano());

        fase.recibirVoto(v1, a);
        fase.recibirVoto(v2, b);

        fase.recibirVoto(v1, a);
        fase.recibirVoto(v2, b);

        AccionVotacion accionPrimeraVuelta = fase.ejecutarResultadoVotacion();

        assertThrows(VotoInvalidoExcepcion.class,
                () -> fase.recibirVoto(v1, c),
                "El sistema debe rechazar votos hacia jugadores que no están en el ballotage");

        fase.recibirVoto(v1, a);
        fase.recibirVoto(v2, a);

        AccionVotacion accionSegundaVuelta = fase.ejecutarResultadoVotacion();

        assertThrows(JugadorMuertoExcepcion.class,
                () -> a.votarComoCiudadano(b, new Urna(new EmpateDiurnoSinEliminacion())),
                "El jugador A debió ser eliminado tras perder el ballotage");

        assertDoesNotThrow(() -> b.votarComoCiudadano(c, new Urna(new EmpateDiurnoSinEliminacion())),
                "El jugador B debe seguir vivo tras salvarse en el ballotage");
    }
}