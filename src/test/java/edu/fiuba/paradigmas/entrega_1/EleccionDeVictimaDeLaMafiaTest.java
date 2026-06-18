package edu.fiuba.paradigmas.entrega_1;

import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fasenocturna.FaseNocturna;
import edu.fiuba.paradigmas.modelo.fasenocturna.accionMafia.AccionMafia;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class EleccionDeVictimaDeLaMafiaTest {

    @Test
    public void laMafiaPuedeSeleccionarUnaVictimaValidaVivaYNoMafiosa() {

        Jugador mafioso1 = new Jugador("mafioso1", new Mafioso());
        Jugador mafioso2 = new Jugador("mafioso2", new Mafioso());
        Jugador ciudadanoVotado = new Jugador("ciudadano votado", new Ciudadano());

        FaseNocturna fase = new FaseNocturna();
        fase.recibirVoto(mafioso1, ciudadanoVotado);
        fase.recibirVoto(mafioso2, ciudadanoVotado);

        AccionMafia resultado = fase.ejecutarResultadoVotacion();

        resultado.ejecutar();

        assertThrows(JugadorMuertoExcepcion.class, () -> fase.recibirVoto(mafioso1, ciudadanoVotado));
    }

}