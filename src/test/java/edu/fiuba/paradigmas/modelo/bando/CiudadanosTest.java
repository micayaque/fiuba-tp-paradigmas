package edu.fiuba.paradigmas.modelo.bando;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CiudadanosTest {

    // Ciudadanos es un Null Object: no tiene la habilidad de verse, así que no hace nada.

    @Test
    public void losCiudadanosNoAgreganAlJugadorVistoPorMafia() {
        Jugador jugador = new Jugador("Bonasera", new Ciudadano());
        List<Jugador> conocidos = new ArrayList<>();

        new Ciudadanos().vistoPorMafia(jugador, conocidos);

        assertTrue(conocidos.isEmpty());
    }

    @Test
    public void losCiudadanosNoIntentanVerANadie() {
        Jugador jugador = new Jugador("Vito", new Ciudadano());
        List<Jugador> conocidos = new ArrayList<>();

        new Ciudadanos().intentarVerA(jugador, conocidos);

        assertTrue(conocidos.isEmpty());
    }

    @Test
    public void losCiudadanosSiSePostulanComoCandidatosParaLaFaseNocturna() {
        Jugador jugador = new Jugador("ciudadano", new Ciudadano());

        List<Jugador> opcionesParaMatar = new ArrayList<>();
        new Ciudadanos().postularseComoCandidatoParaMafia(jugador, opcionesParaMatar);

        assertTrue(opcionesParaMatar.contains(jugador), "El ciudadano debe agregarse a la lista de candidatos");
        assertEquals(1, opcionesParaMatar.size());
    }
}
