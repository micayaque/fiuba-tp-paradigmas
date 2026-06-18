package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Voto;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MuertoTest {

    @Test
    public void unEstadoMuertoActuaComoNullObjectAlPostularse() {
        Estado muerto = new Muerto();
        Jugador jugador = new Jugador("ciudadano", new Ciudadano());

        List<Jugador> opciones = new ArrayList<>();
        muerto.postularseComoCandidatoParaMafia(jugador, opciones);

        assertTrue(opciones.isEmpty(), "El estado Muerto no debe agregar al jugador a las opciones");
    }

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
        Urna urna = new Urna();

        assertThrows(JugadorMuertoExcepcion.class, () -> muerto.intentarVotarComoMafiosoA(jugador, jugador, urna)
        , "Un estado Muerto debe lanzar excepción si se le pide que emita un voto");
    }

    @Test
    public void unEstadoMuertoLanzaExcepcionAlRecibirUnVoto() {
        Estado muerto = new Muerto();
        Jugador victima = new Jugador("ciudadano", new Ciudadano());
        Urna urna = new Urna();

        assertThrows(JugadorMuertoExcepcion.class, () -> muerto.recibirVotoMafioso(victima, new Voto(victima), urna)
        , "Un estado Muerto debe lanzar excepción si intentan meter un voto en su contra en la urna");
    }

    @Test
    public void estadoMuertoLanzaExcepcionAlIntentarInvestigar() {
        Estado muerto = new Muerto();
        Jugador detective = mock(Jugador.class);
        Jugador sospechoso = mock(Jugador.class);

        assertThrows(JugadorMuertoExcepcion.class,
                () -> muerto.intentarInvestigarA(detective, sospechoso));
    }

    @Test
    public void estadoMuertoLanzaExcepcionAlRecibirInvestigacion() {
        Estado muerto = new Muerto();
        Jugador sospechoso = mock(Jugador.class);
        Jugador investigador = mock(Jugador.class);

        assertThrows(JugadorMuertoExcepcion.class,
                () -> muerto.recibirInvestigacion(sospechoso));
    }

    @Test
    public void estadoMuertoLanzaExcepcionAlNominar() {
        Estado muerto = new Muerto();

        assertThrows(JugadorMuertoExcepcion.class, () ->
                muerto.intentarVotarA(mock(Jugador.class), mock(Jugador.class), mock(Urna.class))
        );
    }


}