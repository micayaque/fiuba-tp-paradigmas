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

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadCiudadanos());
        assertEquals(cantidadMedicosEsperadosEnElContador, contador.cantidadMedicos());
        assertEquals(cantidadPadrinosEsperadosEnElContador, contador.cantidadPadrinos());
    }
}
