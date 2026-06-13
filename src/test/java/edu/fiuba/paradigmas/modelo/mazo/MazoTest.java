package edu.fiuba.paradigmas.modelo.mazo;

import edu.fiuba.paradigmas.modelo.excepciones.CantidadDeJugadoresInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.excepciones.ComposicionInvalidaExcepcion;
import edu.fiuba.paradigmas.modelo.rol.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MazoTest {

    @Test
    public void validarAceptaUnaComposicionValidaYDevuelveLosRolesElegidos() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Mafioso(), new Mafioso(), new Detective(), new Medico(),
                                          new Ciudadano(), new Ciudadano(), new Ciudadano());

        assertEquals(rolesElegidos, mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaUnaComposicionSinMafia() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Ciudadano(),
                                          new Ciudadano(), new Detective());

        assertThrows(ComposicionInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaLaMafiaEnMayoria() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Mafioso(), new Mafioso(), new Mafioso(),
                                          new Detective(), new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaMenosDe5Jugadores() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Ciudadano(), new Mafioso());

        assertThrows(CantidadDeJugadoresInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaMasDe12Jugadores() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Mafioso(), new Mafioso(), new Mafioso(), new Padrino(),
                                          new Detective(), new Medico(), new Sheriff(),
                                          new Ciudadano(), new Ciudadano(), new Ciudadano(),
                                          new Ciudadano(), new Ciudadano(), new Ciudadano());

        assertThrows(CantidadDeJugadoresInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaDosPadrinos() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Padrino(), new Padrino(), new Detective(),
                                          new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaDosDetectives() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Mafioso(), new Detective(), new Detective(),
                                          new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaDosSheriffs() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Mafioso(), new Sheriff(), new Sheriff(),
                                          new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaDosMedicos() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Mafioso(), new Medico(), new Medico(),
                                          new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaMasDeUnRolEspecialConMenosDe7Jugadores() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Mafioso(), new Detective(), new Medico(),
                                          new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }

    @Test
    public void rechazaMasDeDosRolesEspecialesConMenosDe10Jugadores() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Mafioso(), new Detective(), new Medico(), new Sheriff(),
                                          new Ciudadano(), new Ciudadano(), new Ciudadano());

        assertThrows(ComposicionInvalidaExcepcion.class, () -> mazo.validar(rolesElegidos));
    }
}
