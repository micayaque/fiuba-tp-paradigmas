package edu.fiuba.paradigmas.entrega_2;

import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Jugador padrino = new Jugador("padrino", new Padrino());
        padrino.morir();

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

        assertThrows(JugadorMuertoExcepcion.class, () -> padrino.votarComoMafiosoA(victima, urnaDePrueba),
                "Un padrino eliminado no debería poder seguir votando");

    }

    @Test
    public void unJugadorEliminadoDebeMostrarSuCarta(){
        Rol rolMafioso = new Mafioso();
        Rol rolCiudadano = new Ciudadano();
        Rol rolDetective = new Detective();
        Rol rolMedico = new Medico();
        Rol rolPadrino = new Padrino();

        Jugador mafioso = new Jugador("mafioso", rolMafioso);
        mafioso.morir();
        Jugador ciudadano = new Jugador("ciudadano", rolCiudadano);
        ciudadano.morir();
        Jugador detective = new Jugador("detective", rolDetective);
        detective.morir();
        Jugador medico = new Jugador("medico", rolMedico);
        medico.morir();
        Jugador padrino = new Jugador("padrino", rolPadrino);
        padrino.morir();

        assertEquals( rolMafioso, mafioso.revelarCarta());
        assertEquals( rolCiudadano, ciudadano.revelarCarta());
        assertEquals( rolDetective, detective.revelarCarta());
        assertEquals( rolMedico, medico.revelarCarta());
        assertEquals( rolPadrino, padrino.revelarCarta());
    }
}