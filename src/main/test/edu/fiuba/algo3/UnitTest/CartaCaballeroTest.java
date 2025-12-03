package edu.fiuba.algo3.UnitTest;

import edu.fiuba.algo3.modelo.Cartas.CartaCaballero;
import edu.fiuba.algo3.modelo.Color;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.Madera;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CartaCaballeroTest {

    @Test
    public void ejecutarEfecto_MueveLadronYTransfiereRecursos() {
        // 1. Arrange
        CartaCaballero carta = new CartaCaballero();
        Jugador jugadorLadron = new Jugador("Rojo", new Color("Rojo"));
        Jugador jugadorVictima = new Jugador("Azul", new Color("Azul"));

        jugadorVictima.agregarRecurso(new Madera(10));

        Tablero tableroMock = mock(Tablero.class);
        int idDestino = 5;

        when(tableroMock.moverLadron(eq(jugadorLadron), eq(idDestino)))
                .thenReturn(List.of(new Color("Azul")));

        carta.setOpciones(idDestino, jugadorVictima);
        carta.nuevoTurno();
        // 2. Act
        carta.ejecutarEfecto(jugadorLadron, tableroMock, List.of(jugadorVictima));

        // 3. Assert
        assertEquals(1, jugadorLadron.contarRecursos(), "El ladron debe recibir 1 recurso");
        assertEquals(9, jugadorVictima.contarRecursos(), "La victima debe perder 1 recurso");

        verify(tableroMock).moverLadron(jugadorLadron, idDestino);
    }
}
