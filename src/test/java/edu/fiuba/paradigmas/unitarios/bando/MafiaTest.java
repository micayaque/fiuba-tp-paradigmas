package edu.fiuba.paradigmas.unitarios.bando;

import edu.fiuba.paradigmas.modelo.bando.Mafia;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MafiaTest {

    @Test
    public void laMafiaAgregaAlJugadorVistoPorMafia() {
        Jugador jugador = new Jugador("Vito", new Mafioso());
        List<Jugador> conocidos = new ArrayList<>();

        new Mafia().vistoPorMafia(jugador, conocidos);

        assertTrue(conocidos.contains(jugador));
    }

    @Test
    public void laMafiaAlIntentarVerAUnJugadorDelegaParaQueSeDejeVer() {
        Jugador otroMafioso = new Jugador("mafioso", new Mafioso());

        List<Jugador> conocidos = new ArrayList<>();
        new Mafia().intentarVerA(otroMafioso, conocidos);

        assertTrue(conocidos.contains(otroMafioso), "La delegación debió llegar hasta el bando y agregarlo");
    }
}
