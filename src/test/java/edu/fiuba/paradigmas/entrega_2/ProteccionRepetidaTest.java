 package edu.fiuba.paradigmas.entrega_2;

 import edu.fiuba.paradigmas.modelo.excepciones.ProteccionRepetidaExcepcion;
 import edu.fiuba.paradigmas.modelo.jugador.Jugador;
 import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
 import edu.fiuba.paradigmas.modelo.rol.Medico;
 import org.junit.jupiter.api.Test;

 import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
 import static org.junit.jupiter.api.Assertions.assertThrows;

 public class ProteccionRepetidaTest {

     @Test
     public void elMedicoNoPuedeProtegerAlMismoJugadorDosNochesConsecutivas() {
         Jugador medico = new Jugador("medico", new Medico());
         Jugador protegido = new Jugador("protegido", new Ciudadano());

         medico.protegerA(protegido);

         assertThrows(ProteccionRepetidaExcepcion.class,
                 () -> medico.protegerA(protegido),
                 "El Médico no debe poder proteger al mismo jugador dos noches consecutivas");
     }

     @Test
     public void elMedicoPuedeVolverAProtegerAUnJugadorSiProtegioAOtroEnElMedio() {
         Jugador medico = new Jugador("medico", new Medico());
         Jugador unJugador = new Jugador("unJugador", new Ciudadano());
         Jugador otroJugador = new Jugador("otroJugador", new Ciudadano());

         medico.protegerA(unJugador);
         medico.protegerA(otroJugador);

         assertDoesNotThrow(() -> medico.protegerA(unJugador),
                 "Proteger a otro jugador en el medio rompe la consecutividad: debe poder repetir");
     }
 }
