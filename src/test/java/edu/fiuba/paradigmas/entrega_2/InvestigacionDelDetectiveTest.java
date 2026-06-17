package edu.fiuba.paradigmas.entrega_2;

import edu.fiuba.paradigmas.modelo.investigacion.ResultadoInvestigacion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Detective;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvestigacionDelDetectiveTest {

    @Test
    public void elDetectiveInvestigaAUnMafiosoYRecibeMafia() {
        Jugador detective = new Jugador("detective", new Detective());
        Jugador mafioso = new Jugador("mafioso", new Mafioso());

        ResultadoInvestigacion resultado = detective.investigarA(mafioso);

        assertEquals("Mafia", resultado.informe(),
                "El Detective debe recibir 'Mafia' al investigar a un Mafioso");
    }

    @Test
    public void elDetectiveInvestigaAUnCiudadanoYRecibeCiudadano() {
        Jugador detective = new Jugador("detective", new Detective());
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());

        ResultadoInvestigacion resultado = detective.investigarA(ciudadano);

        assertEquals("Ciudadano", resultado.informe(),
                "El Detective debe recibir 'Ciudadano' al investigar a un Ciudadano");
    }
}
