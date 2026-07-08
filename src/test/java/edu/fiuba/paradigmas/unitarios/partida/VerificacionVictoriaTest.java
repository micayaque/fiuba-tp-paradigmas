package edu.fiuba.paradigmas.unitarios.partida;

import edu.fiuba.paradigmas.modelo.partida.VerificacionVictoria;
import edu.fiuba.paradigmas.modelo.partida.ResultadoPartida;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VerificacionVictoriaTest {

    @Test
    public void sinMafiososGananLosCiudadanos() {
        VerificacionVictoria recuento = new VerificacionVictoria();
        recuento.sumarCiudadano();
        recuento.sumarCiudadano();

        ResultadoPartida resultado = recuento.determinarResultado();

    }

    @Test
    public void siLaMafiaIgualaOSuperaALosCiudadanosGanaLaMafia() {
        VerificacionVictoria recuento = new VerificacionVictoria();
        recuento.sumarMafioso();
        recuento.sumarCiudadano();

        ResultadoPartida resultado = recuento.determinarResultado();

    }

    @Test
    public void siLaMafiaEsMinoriaLaPartidaSigueEnCurso() {
        VerificacionVictoria recuento = new VerificacionVictoria();
        recuento.sumarMafioso();
        recuento.sumarCiudadano();
        recuento.sumarCiudadano();

        ResultadoPartida resultado = recuento.determinarResultado();

    }
}
