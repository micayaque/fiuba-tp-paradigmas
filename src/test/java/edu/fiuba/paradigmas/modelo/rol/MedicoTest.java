package edu.fiuba.paradigmas.modelo.rol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedicoTest {
    @Test
    public void elMedicoSumaCorrectamenteEnElContadorDeRoles() {
        ContadorDeRoles contador = new ContadorDeRoles();
        Medico medico = new Medico();

        medico.contarseEn(contador);
        medico.contarseEn(contador);
        medico.contarseEn(contador);

        int cantidadDetectivesEsperadosEnElContador = 0;
        int cantidadMafiososEsperadosEnElContador = 0;
        int cantidadCiudadanosEsperadosEnElContador = 0;
        int cantidadMedicosEsperadosEnElContador = 3;

        assertEquals(cantidadDetectivesEsperadosEnElContador, contador.cantidadDetectives());
        assertEquals(cantidadMafiososEsperadosEnElContador, contador.cantidadMafiosos());
        assertEquals(cantidadCiudadanosEsperadosEnElContador, contador.cantidadCiudadanos());
        assertEquals(cantidadMedicosEsperadosEnElContador, contador.cantidadMedicos());
    }
}
