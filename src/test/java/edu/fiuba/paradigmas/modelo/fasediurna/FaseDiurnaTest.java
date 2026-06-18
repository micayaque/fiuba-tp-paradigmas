package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FaseDiurnaTest {

    @Test
    public void faseDiurnaDelegaLaNominacionAlJugador() {
        FaseDiurna fase = new FaseDiurna();

        Jugador nominante = mock(Jugador.class);
        Jugador nominado = mock(Jugador.class);

        fase.recibirNominacion(nominante, nominado);

        verify(nominante).nominarA(eq(nominado), any(Urna.class));
    }
}
