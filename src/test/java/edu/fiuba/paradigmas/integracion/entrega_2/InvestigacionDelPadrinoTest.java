 package edu.fiuba.paradigmas.integracion.entrega_2;

 import edu.fiuba.paradigmas.modelo.bando.Bando;
 import edu.fiuba.paradigmas.modelo.historial.Memento;
 import edu.fiuba.paradigmas.modelo.historial.MementoDeInvestigacion;
 import edu.fiuba.paradigmas.modelo.jugador.Jugador;
 import edu.fiuba.paradigmas.modelo.rol.Detective;
 import edu.fiuba.paradigmas.modelo.rol.Padrino;
 import org.junit.jupiter.api.Test;

 import java.util.ArrayList;
 import java.util.List;

 import static org.junit.jupiter.api.Assertions.assertTrue;

 public class InvestigacionDelPadrinoTest {

     @Test
     public void elDetectiveInvestigaAlPadrinoYRecibeCiudadanoAunqueSeaDeLaMafia() {
         Jugador detective = new Jugador("detective", new Detective());
         Jugador padrino = new Jugador("padrino", new Padrino());

         Memento resultado = detective.investigarA(padrino);
         MementoDeInvestigacion resultadoInvestigacion = (MementoDeInvestigacion) resultado;

         List<Jugador> mafiosos = new ArrayList<>();
         resultadoInvestigacion.bandoDescubierto().vistoPorMafia(padrino, mafiosos);

         assertTrue(mafiosos.isEmpty(),
                 "El bando anotado debió comportarse como Ciudadano y no reaccionar al mensaje vistoPorMafia");
     }
 }
