package edu.fiuba.paradigmas.unitarios.fasediurna;

import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna.VotacionBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

public class VotacionBallotageTest {

    @Test
    public void estadoBallotageRechazaVotoACandidatoNoEmpatado() {
        Jugador a = new Jugador("A", new Ciudadano());
        Jugador b = new Jugador("B", new Ciudadano());
        Jugador c = new Jugador("C", new Ciudadano());
        Urna urnaVotacion = mock(Urna.class);

        VotacionBallotage estado = new VotacionBallotage(List.of(a, b));

        assertThrows(VotoInvalidoExcepcion.class, () -> {
            estado.recibirVoto(mock(Jugador.class), c, urnaVotacion);
        });
    }
}
