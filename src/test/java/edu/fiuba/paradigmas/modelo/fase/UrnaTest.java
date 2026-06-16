package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.fase.urna.Voto;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UrnaTest {

    @Test
    public void devuelveAlJugadorConLaMayoriaDeVotos() {
        Urna urna = new Urna();

        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());

        urna.agregarVoto(new Voto(ciudadano1));
        urna.agregarVoto(new Voto(ciudadano1));
        urna.agregarVoto(new Voto(ciudadano2));

        assertEquals(ciudadano1, urna.jugadorMasVotado(), "Juan debería ser el elegido por tener mayoría");
    }

    @Test
    public void unNuevoCandidatoMayorLimpiaLosEmpatesPrevios() {
        Urna urna = new Urna();
        Jugador empatado1 = new Jugador("A", new Ciudadano());
        Jugador empatado2 = new Jugador("B", new Ciudadano());
        Jugador ganador = new Jugador("C", new Ciudadano());

        urna.agregarVoto(new Voto(empatado1));
        urna.agregarVoto(new Voto(empatado2));

        urna.agregarVoto(new Voto(ganador));
        urna.agregarVoto(new Voto(ganador));

        assertEquals(ganador, urna.jugadorMasVotado(), "El nuevo ganador debió limpiar los empates previos");
    }

}