package edu.fiuba.paradigmas.modelo.rol;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContadorDeRolesTest {

    @Test
    public void contadorDeRolesIncrementaSusVariablesInternasCorrectamente() {
        ContadorDeRoles contadorDeRoles = new ContadorDeRoles();

        contadorDeRoles.sumarMafioso();
        contadorDeRoles.sumarMafioso();
        contadorDeRoles.sumarCiudadano();
        contadorDeRoles.sumarCiudadano();
        contadorDeRoles.sumarCiudadano();
        contadorDeRoles.sumarDetective();
        contadorDeRoles.sumarSheriff();
        contadorDeRoles.sumarMedico();
        contadorDeRoles.sumarPadrino();

        int cantidadMafiososEsperada = 2;
        int cantidadCiudadanosEsperada = 3;
        int cantidadDetectiveEsperada = 1;
        int cantidadSheriffsEsperada = 1;
        int cantidadMedicoEsperada = 1;
        int cantidadPadrinoEsperada = 1;

        assertEquals(cantidadCiudadanosEsperada, contadorDeRoles.cantidadCiudadanos());
        assertEquals(cantidadDetectiveEsperada, contadorDeRoles.cantidadDetectives());
        assertEquals(cantidadMafiososEsperada, contadorDeRoles.cantidadMafiosos());
        assertEquals(cantidadSheriffsEsperada, contadorDeRoles.cantidadSheriffs());
        assertEquals(cantidadMedicoEsperada, contadorDeRoles.cantidadMedicos());
        assertEquals(cantidadPadrinoEsperada, contadorDeRoles.cantidadPadrinos());
    }
}
