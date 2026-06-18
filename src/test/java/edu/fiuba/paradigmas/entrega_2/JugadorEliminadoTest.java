package edu.fiuba.paradigmas.entrega_2;

import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Detective;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Medico;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class JugadorEliminadoTest {

    @Test
    public void unJugadorEliminadoNoPuedeRealizarNingunaAccionActiva() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        mafioso.morir();
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());
        ciudadano.morir();
        Jugador detective = new Jugador("detective", new Detective());
        detective.morir();
        Jugador medico = new Jugador("medico", new Medico());
        medico.morir();

        Jugador victima = new Jugador("Víctima", new Ciudadano());

        Urna urnaDePrueba = new Urna();

        assertThrows(JugadorMuertoExcepcion.class,
                () -> ciudadano.votarA(victima, urnaDePrueba),
                "Un jugador eliminado no debería poder nominar en la fase diurna");

        assertThrows(JugadorMuertoExcepcion.class,
                () -> mafioso.votarComoMafiosoA(victima, urnaDePrueba),
                "Un mafioso eliminado no debería poder seguir votando");

        assertThrows(JugadorMuertoExcepcion.class,
                () -> detective.investigarA(victima),
                "Un detective eliminado no debería poder investigar a otros");

        assertThrows(JugadorMuertoExcepcion.class,
                () -> medico.protegerA(victima),
                "Un médico eliminado no debería poder proteger a nadie");
    }
}