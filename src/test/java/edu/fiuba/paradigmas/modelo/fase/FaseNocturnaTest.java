package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FaseNocturnaTest {

    @Test
    public void recibirVotoYEjecutarResultadoMataAlJugadorMasVotadoYLoDevuelve() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador victima = new Jugador("victima", new Ciudadano());

        FaseNocturna fase = new FaseNocturna(List.of(mafioso, victima));

        fase.recibirVoto(mafioso, victima);
        ResultadoFase resultado = fase.ejecutarResultadoVotacion();

        assertEquals(victima, resultado.jugadorElegidoPorMafia(), "El resultado debe contener a la víctima");

        List<Jugador> vivos = new ArrayList<>();
        victima.estaVivo(vivos);
        assertTrue(vivos.isEmpty(), "La víctima elegida debió haber muerto realmente");
    }
}
