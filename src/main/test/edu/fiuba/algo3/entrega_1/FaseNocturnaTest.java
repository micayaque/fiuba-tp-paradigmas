package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.VictimaInvalida;
import edu.fiuba.algo3.modelo.fases.FaseNocturna;
import edu.fiuba.algo3.modelo.fases.ResultadoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Ciudadano;
import edu.fiuba.algo3.modelo.roles.Mafioso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FaseNocturnaTest {

    @Test
    public void laMafiaPuedeElegirUnaVictimaVivaQueNoSeaDeLaMafia() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador victima = new Jugador("Victima", new Ciudadano());

        ResultadoNocturno resultado = new FaseNocturna()
                .laMafiaVotaA(mafioso, victima)
                .ejecutar();

        assertEquals(victima, resultado.victimaElegida());
        assertTrue(resultado.huboEliminacion());
    }

    @Test
    public void laMafiaNoPuedeElegirUnaVictimaYaEliminada() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador victima = new Jugador("Victima", new Ciudadano());
        victima.eliminar();

        FaseNocturna noche = new FaseNocturna();

        assertThrows(VictimaInvalida.class, () -> noche.laMafiaVotaA(mafioso, victima));
    }

    @Test
    public void laMafiaNoPuedeElegirAOtroMiembroDeLaMafiaComoVictima() {
        Jugador mafioso1 = new Jugador("Mafioso 1", new Mafioso());
        Jugador mafioso2 = new Jugador("Mafioso 2", new Mafioso());

        FaseNocturna noche = new FaseNocturna();

        assertThrows(VictimaInvalida.class, () -> noche.laMafiaVotaA(mafioso1, mafioso2));
    }

    @Test
    public void siElMedicoProtegeALaVictimaLaEliminacionSeAnulaYSigueViva() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador victima = new Jugador("Victima", new Ciudadano());

        ResultadoNocturno resultado = new FaseNocturna()
                .laMafiaVotaA(mafioso, victima)
                .elMedicoProtegeA(victima)
                .ejecutar();

        assertFalse(resultado.huboEliminacion());
        assertTrue(victima.estaVivo());
    }

    @Test
    public void siLaVictimaNoEstaProtegidaEsEliminadaAlTerminarLaNoche() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador victima = new Jugador("Victima", new Ciudadano());
        Jugador otro = new Jugador("Otro", new Ciudadano());

        ResultadoNocturno resultado = new FaseNocturna()
                .laMafiaVotaA(mafioso, victima)
                .elMedicoProtegeA(otro)
                .ejecutar();

        assertTrue(resultado.huboEliminacion());
        assertFalse(victima.estaVivo());
        assertEquals(victima, resultado.jugadorEliminado());
    }

    @Test
    public void siLaMafiaSeAbstieneLaNochePasaSinVictima() {
        Jugador victima = new Jugador("Victima", new Ciudadano());

        ResultadoNocturno resultado = new FaseNocturna()
                .elMedicoProtegeA(victima)
                .ejecutar();

        assertFalse(resultado.huboEliminacion());
        assertTrue(victima.estaVivo());
    }
}
