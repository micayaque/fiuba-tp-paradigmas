package edu.fiuba.paradigmas.unitarios.accionVotacion;

import edu.fiuba.paradigmas.modelo.accionVotacion.IniciarBallotage;
import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IniciarBallotageTest {

    @Test
    public void iniciarBallotageEjecutaElMetodoEnLaFase() {
        FaseDiurna faseMock = mock(FaseDiurna.class);
        List<Jugador> empatados = List.of(mock(Jugador.class));
        IniciarBallotage comando = new IniciarBallotage(empatados);

        comando.ejecutar(faseMock);

        verify(faseMock).iniciarBallotage(empatados);
    }
}
