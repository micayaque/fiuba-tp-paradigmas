package edu.fiuba.paradigmas.modelo.rol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CiudadanoTest {
    @Test
    public void elCiudadanoSumaCorrectamenteEnElContadorDeRoles() {
        ContadorDeRoles contador = new ContadorDeRoles();
        Ciudadano ciudadano = new Ciudadano();

        ciudadano.contarseEn(contador);
        ciudadano.contarseEn(contador);
        ciudadano.contarseEn(contador);

        int cantidadDetectivesEsperadosEnElContador = 0;
        int cantidadMafiososEsperadosEnElContador = 0;
        int cantidadCiudadanosEsperadosEnElContador = 3;

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDeDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadDeMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadDeCiudadanos());
    }
}
