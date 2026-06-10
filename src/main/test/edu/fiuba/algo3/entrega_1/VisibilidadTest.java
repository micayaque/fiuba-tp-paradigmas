package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Ciudadano;
import edu.fiuba.algo3.modelo.roles.Detective;
import edu.fiuba.algo3.modelo.roles.Mafioso;
import edu.fiuba.algo3.modelo.roles.Padrino;
import edu.fiuba.algo3.modelo.roles.Rol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisibilidadTest {

    @Test
    public void unJugadorPuedeVerSuPropioRol() {
        Rol rol = new Ciudadano();
        Jugador ana = new Jugador("Ana", rol);

        assertEquals(rol, ana.miRol());
        assertTrue(ana.conoceElRolDe(ana));
    }

    @Test
    public void unJugadorNoConoceElRolDeLosDemas() {
        Jugador ana = new Jugador("Ana", new Ciudadano());
        Jugador beto = new Jugador("Beto", new Detective());

        assertFalse(ana.conoceElRolDe(beto));
        assertFalse(beto.conoceElRolDe(ana));
    }

    @Test
    public void losMafiososConocenLaIdentidadDeSusComplicesAlIniciarLaPartida() {
        Jugador mafioso1 = new Jugador("Mafioso 1", new Mafioso());
        Jugador mafioso2 = new Jugador("Mafioso 2", new Mafioso());

        assertTrue(mafioso1.conoceElRolDe(mafioso2));
        assertTrue(mafioso2.conoceElRolDe(mafioso1));
    }

    @Test
    public void losMafiososConocenAlPadrinoComoComplice() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador padrino = new Jugador("Padrino", new Padrino());

        assertTrue(mafioso.conoceElRolDe(padrino));
        assertTrue(padrino.conoceElRolDe(mafioso));
    }

    @Test
    public void losMafiososNoConocenElRolDeLosCiudadanos() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador ciudadano = new Jugador("Ciudadano", new Ciudadano());

        assertFalse(mafioso.conoceElRolDe(ciudadano));
        assertFalse(ciudadano.conoceElRolDe(mafioso));
    }

    @Test
    public void unCiudadanoNoConoceElRolDeNadie() {
        Jugador mafioso = new Jugador("Mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("Ciudadano 1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("Ciudadano 2", new Ciudadano());

        assertFalse(ciudadano1.conoceElRolDe(ciudadano2));
        assertFalse(ciudadano1.conoceElRolDe(mafioso));
    }
}
