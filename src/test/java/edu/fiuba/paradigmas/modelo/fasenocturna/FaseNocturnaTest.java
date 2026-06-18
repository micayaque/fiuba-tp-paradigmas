package edu.fiuba.paradigmas.modelo.fasenocturna;

import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class FaseNocturnaTest {

    @Test
    public void recibirVotoLePideAlMafiosoQueVoteEnLaUrna() {
        edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna fase = new edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna();
        Jugador mafioso = mock(Jugador.class);
        Jugador victima = mock(Jugador.class);

        fase.recibirVoto(mafioso, victima);

        verify(mafioso, times(1)).votarComoMafiosoA(eq(victima), any(Urna.class));
    }

    @Test
    public void recibirProteccionLePideAlMedicoQueProteja() {
        edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna fase = new edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna();
        Jugador medico = mock(Jugador.class);
        Jugador protegido = mock(Jugador.class);

        fase.recibirProteccion(medico, protegido);

        verify(medico, times(1)).protegerA(protegido);
    }
    
}