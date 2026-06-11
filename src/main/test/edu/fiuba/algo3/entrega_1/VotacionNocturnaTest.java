package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Ciudadano;
import edu.fiuba.algo3.modelo.votacion.VotacionNocturna;
import edu.fiuba.algo3.modelo.votacion.Voto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VotacionNocturnaTest {

   private Jugador jugador(String nombre) {
       return new Jugador(nombre, new Ciudadano());
   }

   @Test
   public void ganaElJugadorConMasVotos() {
       Jugador a = jugador("A");
       Jugador b = jugador("B");

       VotacionNocturna votacion = new VotacionNocturna();
       votacion.registrarVoto(new Voto(a));
       votacion.registrarVoto(new Voto(a));
       votacion.registrarVoto(new Voto(b));

       assertEquals(a, votacion.resultadoVotacion());
   }
}