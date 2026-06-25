package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fase.ReconocedorFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.Moderador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlternanciaDeFasesTest {

    private static class FaseReconocida implements ReconocedorFase {
        private String fase = "";

        @Override
        public void esNocturna() {
            this.fase = "Nocturna";
        }

        @Override
        public void esDiurna() {
            this.fase = "Diurna";
        }

        public String resultado() {
            return this.fase;
        }
    }

    private String faseDe(Moderador moderador) {
        FaseReconocida reconocida = new FaseReconocida();
        moderador.faseActual().reconocerseEn(reconocida);
        return reconocida.resultado();
    }

    @Test
    public void laPartidaAlternaEntreNocheYDiaIncrementandoLaRondaAlVolverALaNoche() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("ciudadano1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("ciudadano2", new Ciudadano());
        Jugador ciudadano3 = new Jugador("ciudadano3", new Ciudadano());
        Jugador ciudadano4 = new Jugador("ciudadano4", new Ciudadano());
        List<Jugador> jugadores = List.of(mafioso, ciudadano1, ciudadano2, ciudadano3, ciudadano4);

        Moderador moderador = new Moderador(jugadores, new EmpateDiurnoSinEliminacion());

        assertEquals(1, moderador.numeroDeRonda());
        assertEquals("Nocturna", faseDe(moderador));

        moderador.registrarVoto(mafioso, ciudadano1);
        moderador.avanzarFase();

        assertEquals("Diurna", faseDe(moderador), "Tras resolver la noche debe pasar al día, misma ronda");
        assertEquals(1, moderador.numeroDeRonda());

        moderador.registrarNominacion(ciudadano3, ciudadano2);
        moderador.iniciarVotacion();
        moderador.registrarVoto(ciudadano3, ciudadano2);
        moderador.registrarVoto(ciudadano4, ciudadano2);
        moderador.avanzarFase();

        assertEquals("Nocturna", faseDe(moderador), "Tras resolver el día debe volver a la noche");
        assertEquals(2, moderador.numeroDeRonda(), "Al volver a la noche se inicia una nueva ronda");
    }
}
