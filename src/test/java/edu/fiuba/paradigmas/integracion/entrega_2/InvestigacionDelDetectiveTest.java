package edu.fiuba.paradigmas.integracion.entrega_2;

import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeInvestigacion;
import edu.fiuba.paradigmas.modelo.historial.MementoDeProteccion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Detective;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.bando.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InvestigacionDelDetectiveTest {

     @Test
     public void elDetectiveInvestigaAUnMafiosoYRecibeMafia() {
         Jugador detective = new Jugador("detective", new Detective());
         Jugador mafioso = new Jugador("mafioso", new Mafioso());

         Memento resultado = detective.investigarA(mafioso);
         MementoDeInvestigacion resultadoInvestigacion = (MementoDeInvestigacion) resultado;

         List<Jugador> mafiosos = new ArrayList<>();
         resultadoInvestigacion.sospechoso().vistoPorMafia(mafiosos);

         assertFalse(mafiosos.isEmpty(),
                 "El bando anotado debió comportarse como Mafia y reaccionar al mensaje vistoPorMafia");
     }

     @Test
     public void elDetectiveInvestigaAUnCiudadanoYRecibeCiudadano() {
         Jugador detective = new Jugador("detective", new Detective());
         Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());

         Memento resultado = detective.investigarA(ciudadano);
         MementoDeInvestigacion resultadoInvestigacion = (MementoDeInvestigacion) resultado;
         Bando bandoDescubierto = resultadoInvestigacion.bandoDescubierto();

         List<Jugador> mafiosos = new ArrayList<>();
         bandoDescubierto.vistoPorMafia(ciudadano, mafiosos);

         assertTrue(mafiosos.isEmpty(),
                 "El bando anotado debió comportarse como Ciudadano y no reaccionar al mensaje vistoPorMafia");
     }
}