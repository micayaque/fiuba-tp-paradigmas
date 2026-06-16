package edu.fiuba.paradigmas.modelo.rol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PadrinoTest {
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
