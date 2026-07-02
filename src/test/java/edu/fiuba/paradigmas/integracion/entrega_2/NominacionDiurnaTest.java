package edu.fiuba.paradigmas.integracion.entrega_2;

import edu.fiuba.paradigmas.modelo.empate.EmpateDiurnoSinEliminacion;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fase.FaseDiurna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NominacionDiurnaTest {

    @Test
    public void unJugadorMuertoNoPuedeNominarANadie() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());
        Jugador muerto = new Jugador("ciudadano muerto", new Ciudadano());
        Jugador vivo = new Jugador("ciudadano vivo", new Ciudadano());
        muerto.morir();

        assertThrows(JugadorMuertoExcepcion.class,
                () -> fase.recibirNominacion(muerto, vivo),
                "Un muerto no debería poder nominar");
    }

    @Test
    public void noSePuedeNominarAUnJugadorMuerto() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());
        Jugador nominador = new Jugador("nominador", new Ciudadano());
        Jugador nominado = new Jugador("nominado muerto", new Ciudadano());

        nominador.morir();

        assertThrows(JugadorMuertoExcepcion.class,
                () -> fase.recibirNominacion(nominador, nominado),
                "No se debería poder nominar a un jugador muerto");
    }

    @Test
    public void unaNominacionValidaEntreVivosSeRegistraEnLaUrna() {
        FaseDiurna fase = new FaseDiurna(new EmpateDiurnoSinEliminacion());
        Jugador nominador = new Jugador("nominador", new Ciudadano());
        Jugador nominado = new Jugador("nominado", new Ciudadano());

        fase.recibirNominacion(nominador, nominado);

        List<Jugador> nominados = fase.iniciarVotacion();

        assertTrue(nominados.contains(nominado), "El jugador nominado debería aparecer en la lista");
        assertEquals(1, nominados.size(), "Debería haber exactamente un nominado");
    }
}