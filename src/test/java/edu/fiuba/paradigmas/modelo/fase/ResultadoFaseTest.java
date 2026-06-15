package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultadoFaseTest {

    @Test
    public void unResultadoDeFaseDevuelveAlJugadorConElQueFueCreado() {
        Jugador victima = new Jugador("victima", new Ciudadano());
        ResultadoFase resultado = new ResultadoFase(victima);

        assertEquals(victima, resultado.jugadorElegidoPorMafia(),
                "El resultado de la fase debe devolver exactamente al jugador que se le pasó en el constructor");
    }
}