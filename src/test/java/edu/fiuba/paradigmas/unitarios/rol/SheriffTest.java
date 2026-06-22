package edu.fiuba.paradigmas.unitarios.rol;

import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import edu.fiuba.paradigmas.modelo.rol.Sheriff;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SheriffTest {
    @Test
    public void elSheriffSumaCorrectamenteEnElContadorDeRoles() {
        ValidadorDeComposicionDelMazo contador = new ValidadorDeComposicionDelMazo();
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
