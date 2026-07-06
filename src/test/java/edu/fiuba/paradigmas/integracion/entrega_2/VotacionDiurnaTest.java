package edu.fiuba.paradigmas.integracion.entrega_2;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.accionFase.AccionFase;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class VotacionDiurnaTest {

    @Test
    public void elJugadorConMasVotosEsEliminado() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());
        Jugador votante1 = new Jugador("votante 1", new Ciudadano());
        Jugador votante2 = new Jugador("votante 2", new Ciudadano());
        Jugador votado = new Jugador("votado", new Ciudadano());

        fase.recibirVoto(votante1, votado);
        fase.recibirVoto(votante2, votado);

        fase.recibirVoto(votante1, votado);
        fase.recibirVoto(votante2, votado);

        AccionFase accion = fase.ejecutarResultadoVotacion();

        assertThrows(JugadorMuertoExcepcion.class, () -> votado.votarComoCiudadano(votante1, new Urna(new EmpateDiurnoSinEliminacion())));
    }
}
