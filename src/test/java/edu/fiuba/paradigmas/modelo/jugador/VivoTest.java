package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.fase.urna.ResultadoVotacion;
import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.fase.urna.VictimaElegida;
import edu.fiuba.paradigmas.modelo.fase.urna.Voto;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        Urna urna = new Urna();
        vivo.recibirVotoMafioso(victima, new Voto(victima), urna);

        ResultadoVotacion resultadoVotacion = urna.contarVotos();
        resultadoVotacion.resolver().ejecutar();

        List<Jugador> vivos = new ArrayList<>();
        victima.estaVivo(vivos);

        assertTrue(vivos.isEmpty(), "El estado Vivo debe haber metido el voto en la urna, resultando en la muerte de la víctima");
    }

    @Test
    public void unEstadoVivoPasaAlEstadoMuertoAlRecibirMorir() {
        Estado vivo = new Vivo();
        Jugador jugador = new Jugador("ciudadano", new Ciudadano());

        List<Jugador> vivos = new ArrayList<>();
        vivo.morir(jugador);
        jugador.estaVivo(vivos);

        assertTrue(vivos.isEmpty(), "El jugador debió cambiar su estado a Muerto");
    }
}