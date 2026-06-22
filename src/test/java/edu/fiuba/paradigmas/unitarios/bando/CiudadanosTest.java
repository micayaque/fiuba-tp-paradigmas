package edu.fiuba.paradigmas.unitarios.bando;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CiudadanosTest {

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
}
