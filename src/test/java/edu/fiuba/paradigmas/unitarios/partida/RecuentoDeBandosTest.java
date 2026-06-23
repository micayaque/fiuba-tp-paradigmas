package edu.fiuba.paradigmas.unitarios.partida;

import edu.fiuba.paradigmas.modelo.partida.RecuentoDeBandos;
import edu.fiuba.paradigmas.modelo.partida.ResultadoPartida;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RecuentoDeBandosTest {

    @Test
    public void sinMafiososGananLosCiudadanos() {
        RecuentoDeBandos recuento = new RecuentoDeBandos();
        recuento.sumarCiudadano();
        recuento.sumarCiudadano();

        ResultadoPartida resultado = recuento.determinarResultado();

        assertTrue(resultado.partidaTerminada());
        assertEquals("Ganan los Ciudadanos", resultado.anuncio());
    }

    @Test
    public void siLaMafiaIgualaOSuperaALosCiudadanosGanaLaMafia() {
        RecuentoDeBandos recuento = new RecuentoDeBandos();
        recuento.sumarMafioso();
        recuento.sumarCiudadano();

        ResultadoPartida resultado = recuento.determinarResultado();

        assertTrue(resultado.partidaTerminada());
        assertEquals("Gana la Mafia", resultado.anuncio());
    }

    @Test
    public void siLaMafiaEsMinoriaLaPartidaSigueEnCurso() {
        RecuentoDeBandos recuento = new RecuentoDeBandos();
        recuento.sumarMafioso();
        recuento.sumarCiudadano();
        recuento.sumarCiudadano();

        ResultadoPartida resultado = recuento.determinarResultado();

        assertFalse(resultado.partidaTerminada());
        assertEquals("La partida continúa", resultado.anuncio());
    }
}
