package edu.fiuba.algo3.entrega_1.tarotTest;

import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TarotTest {

    @Test
    public void testAplicarTarotACarta() {
        Carta carta = new Carta(new Corazon(),5,5,1);
        Tarot tarot = new Tarot("prueba", "prueba", new Puntaje(10, 1.0), new Modificador("carta"));

        tarot.aplicarA(carta);
        Puntaje puntaje = carta.calcularPuntaje();
        Puntaje puntajeEsperado = new Puntaje(10,1);

        assertTrue(puntaje.compararPuntaje(puntajeEsperado), "El valor de la carta debe aumentar en 10.");
    }


}