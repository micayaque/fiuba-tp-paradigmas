package edu.fiuba.paradigmas.unitarios.urna;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.DeclararNocheSinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fasediurna.FaseDiurna;
import edu.fiuba.paradigmas.modelo.votacion.Voto;
import edu.fiuba.paradigmas.modelo.votacion.VotoDelPadrino;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class VotoDelPadrinoTest {

    private Jugador jugador1;
    private Jugador jugador2;

    @BeforeEach
    public void setUp() {
        jugador1 = mock(Jugador.class);
        jugador2 = mock(Jugador.class);
    }

    @Test
    public void votoDelPadrinoAlAcumularseConVotoComunMantieneSuPoderDeDesempate() {
        Voto votoPadrino = new VotoDelPadrino(jugador1);
        Voto votoComun = new Voto(jugador1);
        Voto acumulado = votoPadrino.acumular(votoComun);

        Voto votoComunDoble = new Voto(jugador1, 2);
        assertTrue(acumulado.mayorEstricto(votoComunDoble));

        Voto votoComunTriple = new Voto(jugador1, 3);
        assertTrue(votoComunTriple.mayorEstricto(acumulado));

        Fase fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());
        AccionVotacion accionPacifica = new DeclararNocheSinJugadorEliminado();
        AccionVotacion resolucion = acumulado.resolverDesempate(accionPacifica);
        resolucion.ejecutar(fase);

        verify(jugador1, times(1)).morir();
    }

    @Test
    public void votoDelPadrinoResuelveEmpateEjecutandoLaMuerteDeSuCandidato() {
        Fase fase = mock(Fase.class);
        Voto votoPadrino = new VotoDelPadrino(jugador1);
        AccionVotacion accionPacifica = new DeclararNocheSinJugadorEliminado();

        AccionVotacion nuevaAccion = votoPadrino.resolverDesempate(accionPacifica);

        nuevaAccion.ejecutar(fase);

        verify(jugador1, times(1)).morir();
        verify(jugador2, never()).morir();
    }

}