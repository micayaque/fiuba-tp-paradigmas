package edu.fiuba.paradigmas.modelo.fasenocturna;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionVotacion.SinJugadorEliminado;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.urna.VotoDelPadrino;
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

        AccionVotacion accionPacifica = new SinJugadorEliminado();
        AccionVotacion resolucion = acumulado.resolverDesempate(accionPacifica);
        resolucion.ejecutar();

        verify(jugador1, times(1)).morir();
    }

    @Test
    public void votoDelPadrinoResuelveEmpateEjecutandoLaMuerteDeSuCandidato() {
        Voto votoPadrino = new VotoDelPadrino(jugador1);
        AccionVotacion accionPacifica = new SinJugadorEliminado();

        AccionVotacion nuevaAccion = votoPadrino.resolverDesempate(accionPacifica);

        nuevaAccion.ejecutar();

        verify(jugador1, times(1)).morir();
        verify(jugador2, never()).morir();
    }

}