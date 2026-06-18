package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.excepciones.CandidatoInvalidoExcepcion;
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
        Urna urna = mock(Urna.class);

        VotacionBallotage estado = new VotacionBallotage(List.of(a, b));

        assertThrows(CandidatoInvalidoExcepcion.class, () -> {
            estado.recibirVoto(mock(Jugador.class), c, urna);
        });
    }
}
