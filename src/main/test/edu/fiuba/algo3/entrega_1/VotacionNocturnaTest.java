package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Ciudadano;
import edu.fiuba.algo3.modelo.votacion.VotacionNocturna;
import edu.fiuba.algo3.modelo.votacion.Voto;
import edu.fiuba.algo3.modelo.votacion.VotoEnBlanco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class VotacionNocturnaTest {

    private Jugador jugador(String nombre) {
        return new Jugador(nombre, new Ciudadano());
    }

    @Test
    public void elRecuentoIgnoraLosVotosEnBlancoYDevuelveElMasVotado() {
        Jugador victima = jugador("Victima");
        Jugador mafioso1 = jugador("Mafioso 1");
        Jugador mafioso2 = jugador("Mafioso 2");
        Jugador ciudadano1 = jugador("Ciudadano 1");
        Jugador ciudadano2 = jugador("Ciudadano 2");

        VotacionNocturna votacion = new VotacionNocturna();
        votacion.registrarVoto(new Voto(mafioso1, victima));
        votacion.registrarVoto(new Voto(mafioso2, victima));
        votacion.registrarVoto(new VotoEnBlanco(ciudadano1));
        votacion.registrarVoto(new VotoEnBlanco(ciudadano2));

        assertEquals(victima, votacion.resultadoVotacion());
    }

    @Test
    public void siTodosVotanEnBlancoNoHayVictima() {
        VotacionNocturna votacion = new VotacionNocturna();
        votacion.registrarVoto(new VotoEnBlanco(jugador("Ciudadano 1")));
        votacion.registrarVoto(new VotoEnBlanco(jugador("Ciudadano 2")));

        assertNull(votacion.resultadoVotacion());
    }

    @Test
    public void ganaElJugadorConMasVotos() {
        Jugador a = jugador("A");
        Jugador b = jugador("B");

        VotacionNocturna votacion = new VotacionNocturna();
        votacion.registrarVoto(new Voto(jugador("Mafioso 1"), a));
        votacion.registrarVoto(new Voto(jugador("Mafioso 2"), a));
        votacion.registrarVoto(new Voto(jugador("Mafioso 3"), b));

        assertEquals(a, votacion.resultadoVotacion());
    }
}
