package edu.fiuba.paradigmas.integracion.entrega_3;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.rol.RolImpostorExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.rol.SheriffYaReveladoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Sheriff;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RevelacionDelSheriffTest {

    @Test
    public void elSheriffSeRevelaUnaVezYQuedaComoObjetivoPrioritario() {
        Jugador sheriff = new Jugador("sheriff", new Sheriff());

        List<Jugador> objetivosAntes = new ArrayList<>();
        sheriff.agregarComoObjetivoPrioritario(objetivosAntes);
        assertTrue(objetivosAntes.isEmpty(), "Un Sheriff sin revelarse no es objetivo prioritario");

        assertDoesNotThrow(sheriff::revelarseComoSheriff);

        List<Jugador> objetivosDespues = new ArrayList<>();
        sheriff.agregarComoObjetivoPrioritario(objetivosDespues);
        assertTrue(objetivosDespues.contains(sheriff),
                "Al revelarse, el Sheriff pasa a ser objetivo prioritario de la Mafia");
    }

    @Test
    public void elSheriffNoPuedeRevelarseDosVeces() {
        Jugador sheriff = new Jugador("sheriff", new Sheriff());

        sheriff.revelarseComoSheriff();

        assertThrows(SheriffYaReveladoExcepcion.class, sheriff::revelarseComoSheriff,
                "El Sheriff solo puede revelarse una vez por partida");
    }

    @Test
    public void unRolQueNoEsSheriffNoPuedeRevelarseComoSheriff() {
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());

        assertThrows(RolImpostorExcepcion.class, ciudadano::revelarseComoSheriff);
    }

    @Test
    public void unSheriffEliminadoNoPuedeRevelarse() {
        Jugador sheriff = new Jugador("sheriff", new Sheriff());
        sheriff.morir();

        assertThrows(JugadorMuertoExcepcion.class, sheriff::revelarseComoSheriff);
    }

    @Test
    public void unCiudadanoComunNuncaEsObjetivoPrioritario() {
        Jugador ciudadano = new Jugador("ciudadano", new Ciudadano());

        List<Jugador> objetivos = new ArrayList<>();
        ciudadano.agregarComoObjetivoPrioritario(objetivos);

        assertFalse(objetivos.contains(ciudadano), "Un ciudadano común no es objetivo prioritario");
    }
}
