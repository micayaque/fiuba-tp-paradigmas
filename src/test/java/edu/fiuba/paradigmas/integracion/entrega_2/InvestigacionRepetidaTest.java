 package edu.fiuba.paradigmas.integracion.entrega_2;

 import edu.fiuba.paradigmas.modelo.excepciones.rol.InvestigacionRepetidaExcepcion;
 import edu.fiuba.paradigmas.modelo.jugador.Jugador;
 import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
 import edu.fiuba.paradigmas.modelo.rol.Detective;
 import edu.fiuba.paradigmas.modelo.rol.Mafioso;
 import org.junit.jupiter.api.Test;

 import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
 import static org.junit.jupiter.api.Assertions.assertThrows;

 public class InvestigacionRepetidaTest {

     @Test
     public void elDetectiveNoPuedeInvestigarAlMismoJugadorDosNochesConsecutivas() {

         Jugador detective = new Jugador("detective", new Detective());
         Jugador investigado = new Jugador("investigado", new Ciudadano());

         assertDoesNotThrow(() -> detective.investigarA(investigado));
         assertThrows(InvestigacionRepetidaExcepcion.class,
                 () -> detective.investigarA(investigado),
                 "El Detective no debe poder investigar al mismo jugador dos noches consecutivas");
     }

     @Test
     public void elDetectivePuedeVolverAInvestigarAUnJugadorSiInvestigoAOtroEnElMedio() {

         Jugador detective = new Jugador("detective", new Detective());
         Jugador unJugador = new Jugador("unJugador", new Ciudadano());
         Jugador otroJugador = new Jugador("otroJugador", new Mafioso());

         detective.investigarA(unJugador);
         detective.investigarA(otroJugador);

         assertDoesNotThrow(() -> detective.investigarA(unJugador),
                 "Investigar a otro jugador en el medio rompe la consecutividad: debe poder repetir");
     }
 }
