package edu.fiuba.paradigmas.unitarios.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.fasediurna.FaseDiurna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Empate;
import edu.fiuba.paradigmas.modelo.urna.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class EmpateDiurnoBallotageTest {

    @Test
    public void laEstrategiaDeBallotageDeberiaIniciarElProcesoCorrectamente() {
        FaseDiurna fase = mock(FaseDiurna.class);
        Urna urna = new Urna(new EmpateDiurnoBallotage());

        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);

        urna.agregarVoto(new Voto(jugador1));
        urna.agregarVoto(new Voto(jugador2));

        ResultadoVotacion resultado = urna.contarVotos();

        EmpateDiurnoBallotage estrategia = new EmpateDiurnoBallotage();

        AccionVotacion accion = estrategia.resolverEmpate((Empate) resultado);
        accion.ejecutar(fase);

        verify(fase, times(1)).iniciarBallotage(((Empate) resultado).empatados());
    }
}
