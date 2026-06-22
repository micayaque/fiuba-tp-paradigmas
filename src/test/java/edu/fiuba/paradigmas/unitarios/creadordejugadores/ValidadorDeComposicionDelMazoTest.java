package edu.fiuba.paradigmas.unitarios.creadordejugadores;

import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ValidadorDeComposicionDelMazoTest {

    @Test
    public void contadorDeRolesIncrementaSusVariablesInternasCorrectamente() {
        ValidadorDeComposicionDelMazo validadorDeComposicionDelMazo = new ValidadorDeComposicionDelMazo();

        validadorDeComposicionDelMazo.sumarMafioso();
        validadorDeComposicionDelMazo.sumarMafioso();
        validadorDeComposicionDelMazo.sumarCiudadano();
        validadorDeComposicionDelMazo.sumarCiudadano();
        validadorDeComposicionDelMazo.sumarCiudadano();
        validadorDeComposicionDelMazo.sumarDetective();
        validadorDeComposicionDelMazo.sumarSheriff();
        validadorDeComposicionDelMazo.sumarMedico();
        validadorDeComposicionDelMazo.sumarPadrino();

        int cantidadMafiososEsperada = 2;
        int cantidadCiudadanosEsperada = 3;
        int cantidadDetectiveEsperada = 1;
        int cantidadSheriffsEsperada = 1;
        int cantidadMedicoEsperada = 1;
        int cantidadPadrinoEsperada = 1;

        assertEquals(cantidadCiudadanosEsperada, validadorDeComposicionDelMazo.cantidadDeCiudadanos());
        assertEquals(cantidadDetectiveEsperada, validadorDeComposicionDelMazo.cantidadDeDetectives());
        assertEquals(cantidadMafiososEsperada, validadorDeComposicionDelMazo.cantidadDeMafiosos());
        assertEquals(cantidadSheriffsEsperada, validadorDeComposicionDelMazo.cantidadDeSheriffs());
        assertEquals(cantidadMedicoEsperada, validadorDeComposicionDelMazo.cantidadDeMedicos());
        assertEquals(cantidadPadrinoEsperada, validadorDeComposicionDelMazo.cantidadDePadrinos());
    }
}
