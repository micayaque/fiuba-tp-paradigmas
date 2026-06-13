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

        assertEquals(cantidadCiudadanosEsperada, contadorDeRoles.cantidadDeCiudadanos());
        assertEquals(cantidadDetectiveEsperada, contadorDeRoles.cantidadDeDetectives());
        assertEquals(cantidadMafiososEsperada, contadorDeRoles.cantidadDeMafiosos());
        assertEquals(cantidadSheriffsEsperada, contadorDeRoles.cantidadDeSheriffs());
        assertEquals(cantidadMedicoEsperada, contadorDeRoles.cantidadDeMedicos());
        assertEquals(cantidadPadrinoEsperada, contadorDeRoles.cantidadDePadrinos());
    }
}
