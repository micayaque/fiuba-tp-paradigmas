package edu.fiuba.paradigmas.integracion.entrega_1;

import edu.fiuba.paradigmas.modelo.fase.FaseNocturna;
import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Medico;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicoAnulaLaEliminacionTest {

    @Test
    public void siElMedicoProtegeALaVictimaLaEliminacionSeAnulaYSigueViva() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador medico = new Jugador("medico", new Medico());
        Jugador victima = new Jugador("victima", new Ciudadano());

        FaseNocturna fase = new FaseNocturna();
        fase.recibirVoto(mafioso, victima);
        fase.recibirProteccion(medico, victima);

        AccionFase accion = fase.ejecutarResultadoVotacion();
        accion.ejecutar(fase);

        List<Jugador> vivos = new ArrayList<>();
        victima.estaVivo(vivos);
        assertTrue(vivos.contains(victima), "El médico protegió a la víctima: debe seguir viva");
    }
}
