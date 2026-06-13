package edu.fiuba.paradigmas.modelo.rol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SheriffTest {
    @Test
    public void elSheriffSumaCorrectamenteEnElContadorDeRoles() {
        ContadorDeRoles contador = new ContadorDeRoles();
        Sheriff sheriff = new Sheriff();

        sheriff.contarseEn(contador);

        int cantidadDetectivesEsperadosEnElContador = 0;
        int cantidadMafiososEsperadosEnElContador = 0;
        int cantidadCiudadanosEsperadosEnElContador = 0;
        int cantidadMedicosEsperadosEnElContador = 0;
        int cantidadSheriffsEsperadosEnElContador = 1;

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadCiudadanos());
        assertEquals(cantidadMedicosEsperadosEnElContador, contador.cantidadMedicos());
        assertEquals(cantidadSheriffsEsperadosEnElContador, contador.cantidadSheriffs());
    }
}
