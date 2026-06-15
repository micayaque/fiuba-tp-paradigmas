package edu.fiuba.paradigmas.modelo.fase;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VotoTest {

    @Test
    public void unVotoRecienCreadoRecuerdaAlVotadoYTieneCantidadUno() {
        Jugador victima = new Jugador("victima", new Ciudadano());
        Voto voto = new Voto(victima);

        assertEquals(victima, voto.votado(), "El voto debe guardar al jugador votado");
        assertTrue(voto.empataCon(new Voto(victima, 1)), "Por defecto un voto vale 1");
    }

    @Test
    public void unVotoEsMayorEstrictoQueOtroSoloSiSuCantidadEsMayor() {
        Jugador victima = new Jugador("victima", new Ciudadano());
        Voto votoMayor = new Voto(victima, 3);
        Voto votoMenor = new Voto(victima, 1);

        assertTrue(votoMayor.mayorEstricto(votoMenor), "3 debería ser mayor estricto que 1");
        assertFalse(votoMenor.mayorEstricto(votoMayor), "1 no debería ser mayor que 3");
        assertFalse(votoMayor.mayorEstricto(new Voto(victima, 3)), "3 no es mayor estricto que 3");
    }

    @Test
    public void dosVotosEmpatanSiTienenLaMismaCantidad() {
        Jugador victima = new Jugador("victima", new Ciudadano());
        Voto voto1 = new Voto(victima, 2);
        Voto voto2 = new Voto(victima, 2);

        assertTrue(voto1.empataCon(voto2), "Dos votos con cantidad 2 deberían empatar");
    }

    @Test
    public void acumularVotosParaElMismoJugadorSumaLasCantidadesYDevuelveUnNuevoVoto() {
        Jugador victima = new Jugador("ciudadano", new Ciudadano());
        Voto votoBase = new Voto(victima, 2);
        Voto votoAAgregar = new Voto(victima, 3);

        Voto votoAcumulado = votoBase.acumular(votoAAgregar);

        assertTrue(votoAcumulado.empataCon(new Voto(victima, 5)), "El acumulado debe tener la suma exacta");
        assertEquals(victima, votoAcumulado.votado(), "Debe mantener al jugador original");
    }

    @Test
    public void acumularVotosDeJugadoresDistintosIgnoraElSegundoVoto() {
        Jugador victima1 = new Jugador("victima1", new Ciudadano());
        Jugador victima2 = new Jugador("victima2", new Ciudadano());

        Voto votoDeJuan = new Voto(victima1, 2);
        Voto votoDePedro = new Voto(victima2, 3);

        Voto votoAcumulado = votoDeJuan.acumular(votoDePedro);

        assertTrue(votoAcumulado.empataCon(new Voto(victima1, 2)), "Debe conservar la cantidad original si los jugadores no coinciden");
        assertEquals(victima1, votoAcumulado.votado(), "El jugador original debe mantenerse intacto");
    }
}