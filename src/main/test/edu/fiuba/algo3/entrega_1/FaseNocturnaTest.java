package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.VictimaInvalida;
import edu.fiuba.algo3.modelo.fases.AccionesNocturnas;
import edu.fiuba.algo3.modelo.fases.FaseNocturna;
import edu.fiuba.algo3.modelo.fases.ResultadoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Ciudadano;
import edu.fiuba.algo3.modelo.roles.Detective;
import edu.fiuba.algo3.modelo.roles.Mafioso;
import edu.fiuba.algo3.modelo.roles.Medico;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FaseNocturnaTest {

    private List<Jugador> jugadores(Jugador... unos) {
        return Arrays.asList(unos);
    }

    @Test
    public void laMafiaPuedeElegirUnaVictimaVivaQueNoSeaDeLaMafia() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador victima = new Jugador("Victima", new Ciudadano());
        Jugador otro = new Jugador("Otro", new Ciudadano());
        List<Jugador> jugadores = jugadores(mafioso, victima, otro);

        AccionesNocturnas acciones = new AccionesNocturnas().mafiosoVotaA(mafioso, victima);
        ResultadoNocturno resultado = new FaseNocturna(acciones).ejecutar(jugadores);

        assertEquals(victima, resultado.victimaElegida());
        assertTrue(resultado.huboEliminacion());
    }

    @Test
    public void laMafiaNoPuedeElegirUnaVictimaYaEliminada() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador victima = new Jugador("Victima", new Ciudadano());
        Jugador otro = new Jugador("Otro", new Ciudadano());
        victima.eliminar();
        List<Jugador> jugadores = jugadores(mafioso, victima, otro);

        AccionesNocturnas acciones = new AccionesNocturnas().mafiosoVotaA(mafioso, victima);

        assertThrows(VictimaInvalida.class, () -> new FaseNocturna(acciones).ejecutar(jugadores));
    }

    @Test
    public void laMafiaNoPuedeElegirAOtroMiembroDeLaMafiaComoVictima() {
        Jugador mafioso1 = new Jugador("Mafioso 1", new Mafioso());
        Jugador mafioso2 = new Jugador("Mafioso 2", new Mafioso());
        Jugador ciudadano = new Jugador("Ciudadano", new Ciudadano());
        List<Jugador> jugadores = jugadores(mafioso1, mafioso2, ciudadano);

        AccionesNocturnas acciones = new AccionesNocturnas().mafiosoVotaA(mafioso1, mafioso2);

        assertThrows(VictimaInvalida.class, () -> new FaseNocturna(acciones).ejecutar(jugadores));
    }

    @Test
    public void siElMedicoProtegeALaVictimaLaEliminacionSeAnulaYSigueViva() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador medico = new Jugador("Medico", new Medico());
        Jugador victima = new Jugador("Victima", new Ciudadano());
        List<Jugador> jugadores = jugadores(mafioso, medico, victima);

        AccionesNocturnas acciones = new AccionesNocturnas()
                .mafiosoVotaA(mafioso, victima)
                .medicoProtegeA(victima);
        ResultadoNocturno resultado = new FaseNocturna(acciones).ejecutar(jugadores);

        assertFalse(resultado.huboEliminacion());
        assertTrue(victima.estaVivo());
    }

    @Test
    public void siLaVictimaNoEstaProtegidaEsEliminadaAlTerminarLaNoche() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador medico = new Jugador("Medico", new Medico());
        Jugador detective = new Jugador("Detective", new Detective());
        Jugador victima = new Jugador("Victima", new Ciudadano());
        Jugador otro = new Jugador("Otro", new Ciudadano());
        List<Jugador> jugadores = jugadores(mafioso, medico, detective, victima, otro);

        AccionesNocturnas acciones = new AccionesNocturnas()
                .mafiosoVotaA(mafioso, victima)
                .medicoProtegeA(otro);
        ResultadoNocturno resultado = new FaseNocturna(acciones).ejecutar(jugadores);

        assertTrue(resultado.huboEliminacion());
        assertFalse(victima.estaVivo());
        assertEquals(victima, resultado.jugadorEliminado());
    }
}
