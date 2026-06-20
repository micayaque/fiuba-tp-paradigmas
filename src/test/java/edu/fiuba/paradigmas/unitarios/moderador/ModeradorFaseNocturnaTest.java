package edu.fiuba.paradigmas.unitarios.moderador;

import edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.moderador.AccionJugador;
import edu.fiuba.paradigmas.modelo.moderador.ModeradorFaseNocturna;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class ModeradorFaseNocturnaTest {

    private FaseNocturna faseMock;
    private ModeradorFaseNocturna moderador;
    private AccionVotacion accionMock;

    @BeforeEach
    public void setUp() {
        faseMock = mock(FaseNocturna.class);
        moderador = new ModeradorFaseNocturna(faseMock);
        accionMock = mock(AccionVotacion.class);

        when(faseMock.ejecutarResultadoVotacion()).thenReturn(accionMock);
    }

    @Test
    public void ejecutarFaseConMedicoAplicaProteccionAntesDeLosVotos() {
        Jugador medico = mock(Jugador.class);
        Jugador protegido = mock(Jugador.class);
        Jugador mafioso = mock(Jugador.class);
        Jugador victima = mock(Jugador.class);

        AccionJugador proteccion = new AccionJugador(medico, protegido);
        AccionJugador voto = new AccionJugador(mafioso, victima);

        moderador.ejecutarFaseNocturna(List.of(voto), proteccion);

        verify(faseMock, times(1)).recibirProteccion(medico, protegido);
        verify(faseMock, times(1)).recibirVoto(mafioso, victima);
        verify(faseMock, times(1)).ejecutarResultadoVotacion();
        verify(accionMock, times(1)).ejecutar(faseMock);
    }

    @Test
    public void ejecutarFaseSinMedicoSoloProcesaVotosYEjecuta() {
        Jugador mafioso = mock(Jugador.class);
        Jugador victima = mock(Jugador.class);
        AccionJugador voto = new AccionJugador(mafioso, victima);

        moderador.ejecutarFaseNocturna(List.of(voto));

        verify(faseMock, never()).recibirProteccion(any(), any());

        verify(faseMock, times(1)).recibirVoto(mafioso, victima);
        verify(faseMock, times(1)).ejecutarResultadoVotacion();
        verify(accionMock, times(1)).ejecutar(faseMock);
    }
}