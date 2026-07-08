package edu.fiuba.paradigmas.unitarios.rol;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.rol.Sheriff;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SheriffTest {
    @Test
    public void elSheriffSumaCorrectamenteEnElContadorDeRoles() {
        Random random = new Random();
        CreadorDeJugadores contador = new CreadorDeJugadores(random);
        Sheriff sheriff = new Sheriff();

        sheriff.contarseEn(contador);

        int cantidadDetectivesEsperadosEnElContador = 0;
        int cantidadMafiososEsperadosEnElContador = 0;
        int cantidadCiudadanosEsperadosEnElContador = 0;
        int cantidadMedicosEsperadosEnElContador = 0;
        int cantidadSheriffsEsperadosEnElContador = 1;

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDeDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadDeMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadDeCiudadanos());
        assertEquals(cantidadMedicosEsperadosEnElContador, contador.cantidadDeMedicos());
        assertEquals(cantidadSheriffsEsperadosEnElContador, contador.cantidadDeSheriffs());
    }
}
