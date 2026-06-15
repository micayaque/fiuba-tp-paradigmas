package edu.fiuba.paradigmas.entrega_1;

import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.fase.ResultadoFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EleccionDeVictimaDeLaMafiaTest {

    @Test
    public void laMafiaPuedeSeleccionarUnaVictimaValidaVivaYNoMafiosa() {

        Jugador mafioso1 = new Jugador("mafioso1", new Mafioso());
        Jugador mafioso2 = new Jugador("mafioso2", new Mafioso());
        Jugador medico = new Jugador("medico", new Medico());
        Jugador ciudadanoVotado = new Jugador("ciudadano votado", new Ciudadano());
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());
        List<Jugador> jugadores = List.of(mafioso1, mafioso2, medico, ciudadanoVotado, ciudadano);

        FaseNocturna fase = new FaseNocturna(jugadores);

        fase.recibirVoto(mafioso1, ciudadanoVotado);
        fase.recibirVoto(mafioso2, ciudadanoVotado);

        ResultadoFase resultado = fase.ejecutarResultadoVotacion();

        assertEquals(resultado.jugadorElegidoPorMafia(), ciudadanoVotado);
        assertThrows(JugadorMuertoExcepcion.class, () -> fase.recibirVoto(mafioso1, ciudadanoVotado));
    }
}