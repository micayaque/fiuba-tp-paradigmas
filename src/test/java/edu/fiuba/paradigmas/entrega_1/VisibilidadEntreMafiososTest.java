package edu.fiuba.paradigmas.entrega_1;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.rol.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VisibilidadEntreMafiososTest {
    @Test
    public void losMafiososConocenASusComplicesDeLaMafia() {
        Jugador mafioso1 = new Jugador("1", new Mafioso());
        Jugador mafioso2 = new Jugador("2", new Mafioso());
        Jugador mafioso3 = new Jugador("3", new Mafioso());

        List<Jugador> conocidos = new ArrayList<>();

        mafioso1.puedeConocerElRolDe(mafioso1, conocidos);
        mafioso1.puedeConocerElRolDe(mafioso2, conocidos);
        mafioso1.puedeConocerElRolDe(mafioso3, conocidos);

        assertEquals(3, conocidos.size(), "El mafioso debería tener 3 personas en su lista");
        assertTrue(conocidos.contains(mafioso1), "El mafioso debería verse a sí mismo");
        assertTrue(conocidos.contains(mafioso2), "El mafioso debería ver a su cómplice");
        assertTrue(conocidos.contains(mafioso3), "El mafioso debería ver a su cómplice");
    }

    @Test
    public void losMafiososNoConocenAOtrosRolesNoMafiosos() {
        Jugador mafioso = new Jugador("mafioso", new Mafioso());
        Jugador ciudadano1 = new Jugador("1", new Ciudadano());
        Jugador ciudadano2 = new Jugador("2", new Ciudadano());

        List<Jugador> conocidos = new ArrayList<>();

        mafioso.puedeConocerElRolDe(mafioso, conocidos);
        mafioso.puedeConocerElRolDe(ciudadano1, conocidos);
        mafioso.puedeConocerElRolDe(ciudadano2, conocidos);

        assertEquals(1, conocidos.size(), "El mafioso solo debería verse a sí mismo y no a los ciudadanos");
        assertTrue(conocidos.contains(mafioso), "El mafioso debería verse a sí mismo");
        assertFalse(conocidos.contains(ciudadano1), "El mafioso NO debería ver al ciudadano 1");
        assertFalse(conocidos.contains(ciudadano2), "El mafioso NO debería ver al ciudadano 2");
    }

}