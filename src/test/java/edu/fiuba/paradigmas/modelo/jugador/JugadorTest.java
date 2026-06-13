package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.rol.ContadorDeRoles;
import edu.fiuba.paradigmas.modelo.rol.Mafioso;
import edu.fiuba.paradigmas.modelo.rol.Rol;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void elJugadorConservaLaCartaConLaQueNace() {
        Rol carta = new Mafioso();
        Jugador jugador = new Jugador("Vito", carta);

        assertSame(carta, jugador.miRol());
    }

    @Test
    public void elJugadorConservaSuNombre() {
        Jugador jugador = new Jugador("Vito", new Mafioso());

        assertEquals("Vito", jugador.nombre());
    }

    @Test
    public void elJugadorSeCuentaSegunSuCartaEnElContador() {
        Jugador jugador = new Jugador("Vito", new Mafioso());
        ContadorDeRoles contador = new ContadorDeRoles();

        jugador.contarseEn(contador);

        assertEquals(1, contador.cantidadDeMafiosos());
        assertEquals(0, contador.cantidadDeCiudadanos());
    }
}
