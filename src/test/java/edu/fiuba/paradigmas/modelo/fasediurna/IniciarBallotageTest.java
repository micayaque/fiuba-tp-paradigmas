package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.accionVotacion.IniciarBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IniciarBallotageTest {

    @Test
    public void iniciarBallotageEjecutaElMetodoEnLaFase() {
        GestorDeBallotage gestorMock = mock(GestorDeBallotage.class);
        List<Jugador> empatados = List.of(mock(Jugador.class));
        IniciarBallotage comando = new IniciarBallotage(gestorMock, empatados);

        comando.ejecutar();

        verify(gestorMock).iniciarBallotage(empatados);
    }
}
