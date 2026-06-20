package edu.fiuba.paradigmas.unitarios.creadordejugadores;

import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.excepciones.mazo.RepartoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreadorDeJugadoresTest {

    private List<String> nombres(int cantidad) {
        List<String> nombres = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            nombres.add("Jugador " + i);
        }
        return nombres;
    }

    @Test
    public void siHayMasNombresQueRolesAsignadosLanzaExcepcionRepartoInvalido() {
        CreadorDeJugadores creador = new CreadorDeJugadores();
        List<String> nombres = nombres(6);
        List<Rol> roles = List.of(new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano(), new Ciudadano());

        assertThrows(RepartoInvalidoExcepcion.class,
                () -> creador.crearPartida(nombres, roles),
                "Debería fallar porque la cantidad de nombres y roles es distinta"
        );
    }

    @Test
    public void fallaSiHayMasRolesQueNombresAsignados() {
        CreadorDeJugadores creador = new CreadorDeJugadores();

        List<String> nombres = nombres(5);
        List<Rol> roles = List.of(new Mafioso(), new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano(), new Ciudadano());

        assertThrows(RepartoInvalidoExcepcion.class,
                () -> creador.crearPartida(nombres, roles),
                "Debería fallar porque la cantidad de roles supera a los nombres"
        );
    }

    @Test
    public void seCreanTantosJugadoresComoNombresYRolesSeanPasados() {
        CreadorDeJugadores creador = new CreadorDeJugadores();

        List<String> nombres = nombres(5);
        List<Rol> roles = List.of(new Mafioso(), new Detective(), new Ciudadano(), new Ciudadano(), new Ciudadano());

        List<Jugador> jugadoresCreados = creador.crearPartida(nombres, roles);

        assertEquals(5, jugadoresCreados.size(), "El creador debe devolver exactamente 5 jugadores instanciados");
    }
}