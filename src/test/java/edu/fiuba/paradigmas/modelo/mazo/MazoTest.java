package edu.fiuba.paradigmas.modelo.mazo;

import edu.fiuba.paradigmas.modelo.excepciones.*;

import edu.fiuba.paradigmas.modelo.rol.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MazoTest {

    @Test
    public void elMazoLanzaLaExcepcionCantidadDeMafiososMayorACero() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Detective(), new Sheriff(), new Sheriff());

        assertThrows(CantidadMafiososEsCeroExcepcion.class,  () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadDeMafiososMayorACantidadCiudadanos() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Mafioso(), new Mafioso(), new Mafioso(), new Sheriff());

        assertThrows(CantidadMafiososExcedidaExcepcion.class,  () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadDeRolesInvalidaCon4Roles() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Ciudadano(), new Mafioso());

        assertThrows(CantidadRolesInvalidaExcepcion.class, () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadDeRolesInvalidaCon13Roles() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Ciudadano(), new Mafioso(),  new Ciudadano(),  new Mafioso(), new Ciudadano(),
                                            new Detective(), new Padrino(), new Medico(), new Sheriff(), new Mafioso(), new Ciudadano());

        assertThrows(CantidadRolesInvalidaExcepcion.class, () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadDePadrinosInvalidaCorrectamente() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Detective(), new Padrino(), new Padrino());

        assertThrows(CantidadRolesEspecialesExcedidaExcepcion.class,  () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadDeDetectivesInvalidaCorrectamente() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Detective(), new Detective(), new Padrino());

        assertThrows(CantidadRolesEspecialesExcedidaExcepcion.class,  () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadDeSheriffsInvalidaCorrectamente() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Mafioso(), new Detective(), new Sheriff(), new Sheriff());

        assertThrows(CantidadRolesEspecialesExcedidaExcepcion.class,  () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadDeMedicosInvalidaCorrectamente() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Mafioso(), new Ciudadano(), new Medico(), new Medico());

        assertThrows(CantidadRolesEspecialesExcedidaExcepcion.class,  () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadRolesEspecialesExcedidaCon2RolesEspecialesY5Jugadores() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Mafioso(), new Medico(), new Padrino());

        assertThrows(CantidadRolesEspecialesExcedidaExcepcion.class, () -> mazo.generarPara(rolesElegidos));
    }

    @Test
    public void elMazoLanzaLaExcepcionCantidadRolesEspecialesExcedidaCon3RolesEspecialesY7Jugadores() {
        Mazo mazo = new Mazo();
        List<Rol> rolesElegidos = List.of(new Ciudadano(), new Ciudadano(), new Mafioso(), new Medico(), new Padrino(),
                                            new Ciudadano(), new Medico());

        assertThrows(CantidadRolesEspecialesExcedidaExcepcion.class, () -> mazo.generarPara(rolesElegidos));
    }
}
