package edu.fiuba.paradigmas.entrega_2;

import edu.fiuba.paradigmas.modelo.investigacion.ResultadoInvestigacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Detective;
import edu.fiuba.paradigmas.modelo.rol.Padrino;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestigacionDelPadrinoTest {

    @Test
    public void elDetectiveInvestigaAlPadrinoYRecibeCiudadanoAunqueSeaDeLaMafia() {
        Jugador detective = new Jugador("detective", new Detective());
        Jugador padrino = new Jugador("padrino", new Padrino());

        ResultadoInvestigacion resultado = detective.investigarA(padrino);

        assertEquals("Ciudadano", resultado.informe(),
                "El Padrino debe aparecer como 'Ciudadano' ante la investigación del Detective");
    }
}
