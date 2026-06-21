package edu.fiuba.paradigmas.unitarios.empate;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.fasediurna.FaseDiurna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.votacion.Empate;
import edu.fiuba.paradigmas.modelo.votacion.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.votacion.Voto;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class EmpateDiurnoBallotageTest {

    @Test
    public void laEstrategiaDeBallotageDeberiaIniciarElProcesoCorrectamente() {
        FaseDiurna fase = mock(FaseDiurna.class);
        UrnaDeVotacion urnaVotacion = new UrnaDeVotacion(new EmpateDiurnoBallotage());

        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);

        urnaVotacion.agregarVoto(new Voto(jugador1));
        urnaVotacion.agregarVoto(new Voto(jugador2));

        ResultadoVotacion resultado = urnaVotacion.contarVotos();

        EmpateDiurnoBallotage estrategia = new EmpateDiurnoBallotage();

        AccionVotacion accion = estrategia.resolverEmpate((Empate) resultado);
        accion.ejecutar(fase);

        verify(fase, times(1)).iniciarBallotage(((Empate) resultado).empatados());
    }
}
