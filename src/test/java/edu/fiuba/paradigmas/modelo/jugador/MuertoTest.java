package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.fase.urna.Voto;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
}