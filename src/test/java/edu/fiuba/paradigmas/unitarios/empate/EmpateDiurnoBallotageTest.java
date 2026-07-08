package edu.fiuba.paradigmas.unitarios.empate;

import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.voto.Voto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class EmpateDiurnoBallotageTest {

    @Test
    public void laEstrategiaDeBallotageDeberiaIniciarElProcesoCorrectamente() {
        FaseDiurna fase = mock(FaseDiurna.class);
        Urna urnaVotacion = new Urna(new EmpateDiurnoBallotage());

        Jugador jugador1 = mock(Jugador.class);
        Jugador jugador2 = mock(Jugador.class);

        Voto voto1 = new Voto(jugador1);
        Voto voto2 = new Voto(jugador2);

        urnaVotacion.agregarVoto(voto1);
        urnaVotacion.agregarVoto(voto2);

        List<Voto> votosEmitidos = new ArrayList<>();
        votosEmitidos.add(voto1);
        votosEmitidos.add(voto2);

        List<Jugador> jugadoresEmpatados = new ArrayList<>();
        jugadoresEmpatados.add(jugador1);
        jugadoresEmpatados.add(jugador2);

        AccionFase resultado = urnaVotacion.contarVotos();

        EmpateDiurnoBallotage estrategia = new EmpateDiurnoBallotage();

        AccionFase accion = estrategia.resolverEmpate(votosEmitidos, jugadoresEmpatados);
        accion.ejecutar(fase);

        verify(fase, times(1)).iniciarBallotage(jugadoresEmpatados);
    }
}
