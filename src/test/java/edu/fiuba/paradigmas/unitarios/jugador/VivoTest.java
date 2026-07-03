package edu.fiuba.paradigmas.unitarios.jugador;

import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.accionjugador.*;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.jugador.*;

import edu.fiuba.paradigmas.modelo.urna.Urna;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class VivoTest {

    @Test
    public void unEstadoVivoAgregaAlJugadorALaListaDeVivos() {
        Estado vivo = new Vivo();
        Jugador jugador = new Jugador("ciudadano", new Ciudadano());

        List<Jugador> vivos = new ArrayList<>();
        vivo.estaVivo(jugador, vivos);

        assertTrue(vivos.contains(jugador), "El estado Vivo debe agregar al jugador a la lista");
        assertEquals(1, vivos.size());
    }

    @Test
    public void unEstadoVivoCreaUnVotoYLoMeteEnLaUrna() {
        Estado vivo = new Vivo();
        Jugador victima = new Jugador("ciudadano", new Ciudadano());
        Jugador votante = new Jugador("mafioso", new Mafioso());

        Urna urnaVotacion = new Urna(new EmpateDiurnoSinEliminacion());
        AccionJugador comando = new VotarComoMafioso(votante, victima, urnaVotacion);
        vivo.procesarAccion(comando);

        AccionVotacion resultadoVotacion = urnaVotacion.contarVotos();
        resultadoVotacion.ejecutar(new FaseNocturna());

        List<Jugador> vivos = new ArrayList<>();
        victima.estaVivo(vivos);

        assertTrue(vivos.isEmpty(), "El estado Vivo debe haber metido el voto en la urna, resultando en la muerte de la víctima");
    }

    @Test
    public void unEstadoVivoPasaAlEstadoMuertoAlRecibirMorir() {
        Estado vivo = new Vivo();
        Jugador jugador = new Jugador("ciudadano", new Ciudadano());

        List<Jugador> vivos = new ArrayList<>();
        AccionJugador comando = new RecibirEliminacion(jugador);
        vivo.procesarAccion(comando);
        jugador.estaVivo(vivos);

        assertTrue(vivos.isEmpty(), "El jugador debió cambiar su estado a Muerto");
    }

    @Test
    public void estadoVivoPermiteIntentarInvestigarYDelegaEnElDetective() {
        Estado vivo = new Vivo();
        Jugador detectiveMock = mock(Jugador.class);
        Jugador sospechosoMock = mock(Jugador.class);
        AccionJugador comando = new Investigar(detectiveMock, sospechosoMock);
        vivo.procesarAccion(comando);

        verify(detectiveMock, times(1)).continuarInvestigacionA(sospechosoMock);
    }

    @Test
    public void estadoVivoPermiteRecibirInvestigacionYDelegaEnElSospechoso() {
        Estado vivo = new Vivo();
        Jugador sospechosoMock = mock(Jugador.class);
        Jugador investigadorMock = mock(Jugador.class);
        AccionJugador comando = new RecibirInvestigacion(sospechosoMock);
        vivo.procesarAccion(comando);

        verify(sospechosoMock, times(1)).continuarRevelandoIdentidad();
    }
}