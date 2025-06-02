package edu.fiuba.algo3.entrega_1.puntajeTest;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PuntajeTest {
    @Test
    void test01PuntajeConValor10YMultiplicador2Da20Puntos(){
        //assert
        Puntaje puntaje = new Puntaje(10,2);

        //act
        int puntajeObtenido = puntaje.calcularPuntaje();

        //assert
        assertEquals(20,puntajeObtenido);
    }

    @Test
    void test02ModificoMultiplicadorDeUnPuntaje(){
        //assert
        Puntaje puntaje = new Puntaje(10,2);

        //act
        puntaje.modificarMultiplicador(2);
        int puntajeObtenido = puntaje.calcularPuntaje();

        //assert
        assertEquals(40,puntajeObtenido);
    }

    @Test
    void test03ModificoValorDeUnPuntaje(){
        //assert
        Puntaje puntaje = new Puntaje(10,2);

        //act
        puntaje.modificarValor(5);
        int puntajeObtenido = puntaje.calcularPuntaje();

        //assert
        assertEquals(30,puntajeObtenido);
    }

    @Test
    void test04SeSuman2PuntajesDistintos(){
        //arrange
        Puntaje puntaje = new Puntaje(10,2);
        Puntaje otroPuntaje = new Puntaje(5,3);

        //act
        Puntaje suma = puntaje.sumarConPuntaje(otroPuntaje);

        //asser
        Assertions.assertTrue(suma.compararPuntaje(new Puntaje(15,5)));
    }
}
