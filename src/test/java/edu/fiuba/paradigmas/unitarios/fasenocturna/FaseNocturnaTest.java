package edu.fiuba.paradigmas.unitarios.fasenocturna;

import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class FaseNocturnaTest {

    @Test
    public void recibirVotoLePideAlMafiosoQueVoteEnLaUrna() {
        FaseNocturna fase = new FaseNocturna();
        Jugador mafioso = mock(Jugador.class);
        Jugador victima = mock(Jugador.class);

        fase.recibirVoto(mafioso, victima);

        verify(mafioso, times(1)).votarComoMafiosoA(eq(victima), any(Urna.class));
    }

    @Test
    public void recibirProteccionLePideAlMedicoQueProteja() {
        FaseNocturna fase = new FaseNocturna();
        Jugador medico = mock(Jugador.class);
        Jugador protegido = mock(Jugador.class);

        fase.recibirProteccion(medico, protegido);

        verify(medico, times(1)).protegerA(protegido);
    }
    
}