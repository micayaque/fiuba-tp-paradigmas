package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.excepciones.InvestigacionRepetidaExcepcion;
import edu.fiuba.paradigmas.modelo.investigacion.ResultadoInvestigacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DetectiveTest {
    @Test
    public void elDetectiveSumaCorrectamenteEnElContadorDeRoles() {
        ContadorDeRoles contador = new ContadorDeRoles();
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
    public void elDetectiveAlInvestigarDelegaEnElJugadorYRecibeSuApariencia() {
        Detective detective = new Detective();
        Jugador mafioso = new Jugador("mafioso", new Mafioso());

        ResultadoInvestigacion resultado = detective.investigarComoDetectiveA(mafioso);

        assertEquals("Mafia", resultado.informe(), "El Detective debió delegar en el jugador para conocer su apariencia");
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
