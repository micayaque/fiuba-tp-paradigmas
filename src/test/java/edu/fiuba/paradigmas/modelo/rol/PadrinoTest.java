package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PadrinoTest {

    @Test
    public void alRevelarSuIdentidadElPadrinoMienteYSePresentaComoCiudadano() {
        Padrino rolPadrino = new Padrino();
        Jugador jugadorPadrino = new Jugador("Padrino", rolPadrino);

        Bando bandoRevelado = rolPadrino.revelarBando();

        List<Jugador> postulantes = new ArrayList<>();
        List<Jugador> vistosPorMafia = new ArrayList<>();

        bandoRevelado.postularseComoCandidatoParaMafia(jugadorPadrino, postulantes);
        assertTrue(postulantes.contains(jugadorPadrino),
                "El bando devuelto debió comportarse como Ciudadano al postularse");

        bandoRevelado.vistoPorMafia(jugadorPadrino, vistosPorMafia);
        assertFalse(vistosPorMafia.contains(jugadorPadrino),
                "El bando devuelto no debió revelarse como Mafia");
    }

    @Test
    public void elPadrinoSumaCorrectamenteEnElContadorDeRoles() {
        ContadorDeRoles contador = new ContadorDeRoles();
        Padrino padrino = new Padrino();

        padrino.contarseEn(contador);

        int cantidadDetectivesEsperadosEnElContador = 0;
        int cantidadMafiososEsperadosEnElContador = 0;
        int cantidadCiudadanosEsperadosEnElContador = 0;
        int cantidadMedicosEsperadosEnElContador = 0;
        int cantidadPadrinosEsperadosEnElContador = 1;

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDeDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadDeMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadDeCiudadanos());
        assertEquals(cantidadMedicosEsperadosEnElContador, contador.cantidadDeMedicos());
        assertEquals(cantidadPadrinosEsperadosEnElContador, contador.cantidadDePadrinos());
    }
}
