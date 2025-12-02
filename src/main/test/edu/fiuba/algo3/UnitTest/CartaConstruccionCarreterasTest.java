package edu.fiuba.algo3.UnitTest;

import edu.fiuba.algo3.modelo.Cartas.CartaConstruccionCarreteras;
import edu.fiuba.algo3.modelo.Color;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Recursos.*;
import edu.fiuba.algo3.modelo.Tablero.ConstruccionExistenteException;
import edu.fiuba.algo3.modelo.Tablero.Factory.Coordenada;
import edu.fiuba.algo3.modelo.Tablero.Factory.ReglaConstruccionException;
import edu.fiuba.algo3.modelo.Tablero.Tablero;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CartaConstruccionCarreterasTest {
    @Test
    public void ejecutarEfecto_ConstruyeGratis_Y_RestauraCobroNormal() throws ConstruccionExistenteException, ReglaConstruccionException {
        // 1. Arrange
        CartaConstruccionCarreteras carta = new CartaConstruccionCarreteras();
        Jugador jugador = new Jugador("Rojo", new Color("Rojo"));

        jugador.agregarRecurso(new Madera(1));
        jugador.agregarRecurso(new Ladrillo(1));

        int maderaInicial = jugador.cantidadMadera();
        int ladrilloInicial = jugador.cantidadLadrillo();

        Tablero tableroMock = mock(Tablero.class);
        Coordenada coord1 = mock(Coordenada.class);
        Coordenada coord2 = mock(Coordenada.class);
        //act
        carta.setCoordenadas(coord1, coord2);
        carta.nuevoTurno();

        carta.ejecutarEfecto(jugador, tableroMock, List.of());
        //assert
        assertEquals(maderaInicial, jugador.cantidadMadera(), "La madera no debe consumirse con la carta");
        assertEquals(ladrilloInicial, jugador.cantidadLadrillo(), "El Ladrillo no debe consumirse con la carta");


        Coordenada coord3 = mock(Coordenada.class);
        jugador.construirCarretera(tableroMock, coord3);

        assertEquals(0, jugador.cantidadMadera(), "La estrategia de pago (cobrar)");
        assertEquals(0, jugador.cantidadLadrillo(), "La estrategia de pago  (cobrar)");
    }
}
