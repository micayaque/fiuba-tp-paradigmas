package edu.fiuba.algo3.UnitTest;

import edu.fiuba.algo3.modelo.Cartas.CartaMonopolio;
import edu.fiuba.algo3.modelo.Color;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CartaMonopolioTest {
    @Test
    public void ejecutarEfecto_RobaTodosLosRecursosDelTipoElegido() {
        // 1. Arrange
        CartaMonopolio monopolio = new CartaMonopolio();

        Jugador ladron = new Jugador("Rojo", new Color("Rojo"));
        Jugador victima1 = new Jugador("Azul", new Color("Azul"));
        Jugador victima2 = new Jugador("Verde", new Color("Verde"));

        victima1.agregarRecurso(new Lana(3));
        victima1.agregarRecurso(new Madera(5));

        victima2.agregarRecurso(new Lana(1));


        monopolio.setRecursoElegido(new Lana(0));
        monopolio.nuevoTurno(); // Habilitar uso

        Tablero tableroIgnorado = mock(Tablero.class);
        List<Jugador> oponentes = List.of(victima1, victima2);

        // 2. Act
        monopolio.ejecutarEfecto(ladron, tableroIgnorado, oponentes);

        // 3. Assert
        assertEquals(4, ladron.cantidadLana(), "El ladron debe tener toda la Lana");

        assertEquals(0, victima1.cantidadLana(), "victima 1 debe perder su Lana");
        assertEquals(0, victima2.cantidadLana(), "victima 2 debe perder su Lana");
        assertEquals(5, victima1.cantidadMadera(), "La madera intacta");
    }
}
