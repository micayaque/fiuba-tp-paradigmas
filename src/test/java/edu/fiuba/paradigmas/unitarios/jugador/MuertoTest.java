package edu.fiuba.paradigmas.unitarios.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.*;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoBallotage;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Estado;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.jugador.Muerto;
import edu.fiuba.paradigmas.modelo.urna.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.voto.Voto;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MuertoTest {

    @Test
    public void unEstadoMuertoActuaComoNullObjectAlAgregarseALosVivos() {
        Estado muerto = new Muerto();
        Jugador jugador = new Jugador("ciudadano", new Ciudadano());

        List<Jugador> vivos = new ArrayList<>();
        muerto.estaVivo(jugador, vivos);

        assertTrue(vivos.isEmpty(), "El estado Muerto no debe agregarse a la lista de vivos");
    }

    @Test
    public void unEstadoMuertoLanzaExcepcionAlIntentarVotar() {
        Estado muerto = new Muerto();
        Jugador jugador = new Jugador("ciudadano", new Ciudadano());
        UrnaDeVotacion urnaVotacion = new UrnaDeVotacion(new EmpateDiurnoSinEliminacion());

        AccionJugador comando = new VotarComoMafioso(jugador, jugador, urnaVotacion);
        assertThrows(JugadorMuertoExcepcion.class, () -> muerto.procesarAccion(comando)
        , "Un estado Muerto debe lanzar excepción si se le pide que emita un voto");
    }

    @Test
    public void unEstadoMuertoLanzaExcepcionAlRecibirUnVoto() {
        Estado muerto = new Muerto();
        Jugador victima = new Jugador("ciudadano", new Ciudadano());
        UrnaDeVotacion urnaVotacion = new UrnaDeVotacion(new EmpateDiurnoBallotage());

        AccionJugador comando = new RecibirVotoNocturno(victima, new Voto(victima), urnaVotacion);

        assertThrows(JugadorMuertoExcepcion.class, () -> muerto.procesarAccion(comando)
        , "Un estado Muerto debe lanzar excepción si intentan meter un voto en su contra en la urna");
    }

    @Test
    public void estadoMuertoLanzaExcepcionAlIntentarInvestigar() {
        Estado muerto = new Muerto();
        Jugador detective = mock(Jugador.class);
        Jugador sospechoso = mock(Jugador.class);

        AccionJugador comando = new Investigar(detective, sospechoso);

        assertThrows(JugadorMuertoExcepcion.class,
                () -> muerto.procesarAccion(comando));
    }

    @Test
    public void estadoMuertoLanzaExcepcionAlRecibirInvestigacion() {
        Estado muerto = new Muerto();
        Jugador sospechoso = mock(Jugador.class);
        Jugador investigador = mock(Jugador.class);

        AccionJugador comando = new RecibirInvestigacion(sospechoso);

        assertThrows(JugadorMuertoExcepcion.class,
                () -> muerto.procesarAccion(comando));
    }

    @Test
    public void estadoMuertoLanzaExcepcionAlNominar() {
        Estado muerto = new Muerto();

        AccionJugador comando = new VotarComoCiudadano(mock(Jugador.class), mock(Jugador.class), mock(UrnaDeVotacion.class));

        assertThrows(JugadorMuertoExcepcion.class, () ->
                muerto.procesarAccion(comando)
        );
    }
}