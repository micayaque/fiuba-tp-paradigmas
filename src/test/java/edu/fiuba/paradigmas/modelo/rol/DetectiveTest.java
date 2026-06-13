package edu.fiuba.paradigmas.modelo.rol;

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
}
