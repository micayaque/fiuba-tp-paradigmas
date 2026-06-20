package edu.fiuba.paradigmas.integracion.entrega_2;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fasediurna.FaseDiurna;
import edu.fiuba.paradigmas.modelo.accionVotacion.AccionVotacion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VotacionDiurnaTest {

    @Test
    public void elJugadorConMasVotosEsEliminado() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());
        Jugador votante1 = new Jugador("votante 1", new Ciudadano());
        Jugador votante2 = new Jugador("votante 2", new Ciudadano());
        Jugador votado = new Jugador("votado", new Ciudadano());

        fase.recibirNominacion(votante1, votado);
        fase.recibirNominacion(votante2, votado);

        fase.iniciarVotacion();

        fase.recibirVoto(votante1, votado);
        fase.recibirVoto(votante2, votado);

        AccionVotacion accion = fase.ejecutarResultadoVotacion();
        accion.ejecutar(fase);

        assertThrows(JugadorMuertoExcepcion.class, () -> votado.votarA(votante1, new Urna(new EmpateDiurnoSinEliminacion())));
    }
}
