package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class EmpateDiurnoBallotageTest {

    @Test
    public void laEstrategiaDeBallotageDeberiaIniciarElProcesoCorrectamente() {
        GestorDeBallotage gestor = mock(GestorDeBallotage.class);
        List<Jugador> empatados = List.of(mock(Jugador.class));
        EmpateDiurnoBallotage estrategia = new EmpateDiurnoBallotage(gestor);

        AccionVotacion accion = estrategia.resolverEmpate(empatados, new ArrayList<>());
        accion.ejecutar();

        verify(gestor, times(1)).iniciarBallotage(empatados);
    }
}
