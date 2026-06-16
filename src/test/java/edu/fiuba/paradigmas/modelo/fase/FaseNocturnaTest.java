package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.fase.accionMafia.AccionMafia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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