package edu.fiuba.paradigmas.modelo.reparto;

import edu.fiuba.paradigmas.modelo.excepciones.RepartoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.Ciudadano;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Rol;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class RepartidorTest {

    @Test
    public void repartirCreaUnJugadorPorNombreEnElMismoOrden() {
        List<Rol> roles = List.of(new Mafioso(), new Ciudadano(), new Ciudadano());

        List<Jugador> jugadores = new Repartidor().repartir(List.of("Ana", "Beto", "Cami"), roles);

        assertEquals(3, jugadores.size());
        assertEquals("Ana", jugadores.get(0).nombre());
        assertEquals("Beto", jugadores.get(1).nombre());
        assertEquals("Cami", jugadores.get(2).nombre());
    }

    @Test
    public void repartirAsignaTodasLasCartasSinPerderlasNiDuplicarlas() {
        Rol unaCarta = new Mafioso();
        Rol otraCarta = new Ciudadano();
        Rol unaTercera = new Ciudadano();

        List<Jugador> jugadores = new Repartidor()
                .repartir(List.of("Ana", "Beto", "Cami"), List.of(unaCarta, otraCarta, unaTercera));

        Set<Rol> cartasRepartidas = new HashSet<>();
        for (Jugador jugador : jugadores) {
            cartasRepartidas.add(jugador.miRol());
        }

        assertEquals(Set.of(unaCarta, otraCarta, unaTercera), cartasRepartidas);
    }

    @Test
    public void repartirNoMutaLaListaDeRolesRecibida() {
        List<Rol> roles = new ArrayList<>(List.of(new Mafioso(), new Ciudadano(), new Ciudadano()));
        List<Rol> ordenOriginal = new ArrayList<>(roles);

        new Repartidor().repartir(List.of("Ana", "Beto", "Cami"), roles);

        assertEquals(ordenOriginal, roles);
    }

    @Test
    public void repartirRechazaCuandoLosRolesNoCoincidenConLosNombres() {
        List<Rol> roles = List.of(new Mafioso(), new Ciudadano());

        assertThrows(RepartoInvalidoExcepcion.class,
                () -> new Repartidor().repartir(List.of("Ana", "Beto", "Cami"), roles));
    }
}
