package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.excepciones.CantidadDeJugadoresInvalida;
import edu.fiuba.algo3.modelo.mazo.Mazo;
import edu.fiuba.algo3.modelo.roles.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MazoTest {

    private long contar(List<Rol> roles, Class<? extends Rol> tipo) {
        return roles.stream().filter(rol -> rol.getClass() == tipo).count();
    }

    private long contarMafia(List<Rol> roles) {
        return roles.stream().filter(rol -> rol.bando().esMafia()).count();
    }

    @Test
    public void mazoPara5JugadoresTieneLaComposicionEsperada() {
        List<Rol> roles = new Mazo().generarPara(5);

        assertEquals(5, roles.size());
        assertEquals(1, contar(roles, Mafioso.class));
        assertEquals(0, contar(roles, Padrino.class));
        assertEquals(1, contar(roles, Detective.class));
        assertEquals(1, contar(roles, Medico.class));
        assertEquals(0, contar(roles, Sheriff.class));
        assertEquals(2, contar(roles, Ciudadano.class));
    }

    @Test
    public void mazoPara7JugadoresTieneLaComposicionEsperada() {
        List<Rol> roles = new Mazo().generarPara(7);

        assertEquals(7, roles.size());
        assertEquals(2, contar(roles, Mafioso.class));
        assertEquals(0, contar(roles, Padrino.class));
        assertEquals(1, contar(roles, Detective.class));
        assertEquals(1, contar(roles, Medico.class));
        assertEquals(0, contar(roles, Sheriff.class));
        assertEquals(3, contar(roles, Ciudadano.class));
    }

    @Test
    public void mazoPara10JugadoresIncluyePadrinoYSheriff() {
        List<Rol> roles = new Mazo().generarPara(10);

        assertEquals(10, roles.size());
        assertEquals(2, contar(roles, Mafioso.class));
        assertEquals(1, contar(roles, Padrino.class));
        assertEquals(1, contar(roles, Detective.class));
        assertEquals(1, contar(roles, Medico.class));
        assertEquals(1, contar(roles, Sheriff.class));
        assertEquals(4, contar(roles, Ciudadano.class));
    }

    @Test
    public void laMafiaEsSiempreMinoriaParaCualquierCantidadValida() {
        for (int cantidad = 5; cantidad <= 12; cantidad++) {
            List<Rol> roles = new Mazo().generarPara(cantidad);

            long mafia = contarMafia(roles);
            long ciudadanos = roles.size() - mafia;

            assertEquals(cantidad, roles.size(), "Mazo para " + cantidad + " jugadores");
            assertTrue(mafia < ciudadanos, "La mafia debe ser minoria con " + cantidad + " jugadores");
        }
    }

    @Test
    public void mazoConPocosJugadoresEsRechazado() {
        assertThrows(CantidadDeJugadoresInvalida.class, () -> new Mazo().generarPara(4));
    }

    @Test
    public void mazoConDemasiadosJugadoresEsRechazado() {
        assertThrows(CantidadDeJugadoresInvalida.class, () -> new Mazo().generarPara(13));
    }
}
