package edu.fiuba.algo3.UnitTest;

import edu.fiuba.algo3.modelo.Cartas.CartaDescubrimiento;
import edu.fiuba.algo3.modelo.Color;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.*;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CartaDescubrimientoTest {
    @Test
    public void ejecutarEfecto_OtorgaDosRecursosAlJugador() {
        // 1. Arrange
        CartaDescubrimiento carta = new CartaDescubrimiento();
        Jugador jugador = new Jugador("Rojo", new Color("Rojo"));

        int recursosIniciales = jugador.totalRecursos();

        List<TipoDeRecurso> recursosDeseados = List.of(new Lana(1), new Mineral(1));

        carta.setRecursosDeseados(recursosDeseados);
        carta.nuevoTurno();

        Tablero tablero = mock(Tablero.class);
        //act
        carta.ejecutarEfecto(jugador, tablero, List.of());

        assertEquals(recursosIniciales + 2, jugador.totalRecursos(), "Debe añadir  2 recursos");

        assertEquals(1, jugador.cantidadLana(), "Debe tener 1 lana");
        assertEquals(1, jugador.cantidadMineral(), "Debe tener 1 Mineral");
    }
}
