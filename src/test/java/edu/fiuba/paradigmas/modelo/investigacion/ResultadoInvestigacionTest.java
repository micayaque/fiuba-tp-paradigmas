package edu.fiuba.paradigmas.modelo.investigacion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultadoInvestigacionTest {

    @Test
    public void elResultadoMafiaInformaMafia() {
        ResultadoInvestigacion resultado = new ResultadoMafia();

        assertEquals("Mafia", resultado.informe());
    }

    @Test
    public void elResultadoCiudadanoInformaCiudadano() {
        ResultadoInvestigacion resultado = new ResultadoCiudadano();

        assertEquals("Ciudadano", resultado.informe());
    }
}
