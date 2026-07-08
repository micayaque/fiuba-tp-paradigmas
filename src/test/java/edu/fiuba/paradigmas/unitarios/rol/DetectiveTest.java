package edu.fiuba.paradigmas.unitarios.rol;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.excepciones.rol.InvestigacionRepetidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorVivoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Detective;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Rol;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class DetectiveTest {
    @Test
    public void elDetectiveSumaCorrectamenteEnElContadorDeRoles() {
        Random random = new Random();
        CreadorDeJugadores contador = new CreadorDeJugadores(random);
        Detective detective = new Detective();

        detective.contarseEn(contador);

        int cantidadDetectivesEsperadosEnElContador = 1;
        int cantidadMafiososEsperadosEnElContador = 0;
        int cantidadCiudadanosEsperadosEnElContador = 0;

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDeDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadDeMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadDeCiudadanos());
    }

    @Test
    public void unDetectiveEliminadoDebeMostrarSuCarta(){
        Rol rolDetective = new Detective();
        Jugador detective = new Jugador("detective", rolDetective);

        detective.morir();

        assertEquals( rolDetective, detective.revelarCarta());
    }

    @Test
    public void unDetectiveVivoNoDebeMostrarSuCarta(){
        Rol rolDetective = new Detective();
        Jugador detective = new Jugador("detective", rolDetective);

        assertThrows(JugadorVivoExcepcion.class, detective::revelarCarta,
                "Un detective no debería mostrar su carta si está vivo");
    }

    @Test
    public void elDetectiveNoPuedeInvestigarAlMismoJugadorDosVecesSeguidas() {
        Detective detective = new Detective();
        Jugador investigado = new Jugador("investigado", new Ciudadano());

        detective.investigarComoDetectiveA(investigado);

        assertThrows(InvestigacionRepetidaExcepcion.class,
                () -> detective.investigarComoDetectiveA(investigado),
                "Investigar al mismo jugador dos noches seguidas debe ser rechazado");
    }

    @Test
    public void elDetectivePuedeRepetirInvestigacionSiInvestigoAOtroEnElMedio() {
        Detective detective = new Detective();
        Jugador unJugador = new Jugador("unJugador", new Ciudadano());
        Jugador otroJugador = new Jugador("otroJugador", new Mafioso());

        detective.investigarComoDetectiveA(unJugador);
        detective.investigarComoDetectiveA(otroJugador);

        assertDoesNotThrow(() -> detective.investigarComoDetectiveA(unJugador),
                "Tras investigar a otro, la consecutividad se rompe y puede repetir");
    }
}
