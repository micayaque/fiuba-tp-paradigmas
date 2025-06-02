package edu.fiuba.algo3.entrega_1.generadorDeCartasTest;

import edu.fiuba.algo3.modelo.GeneradorDeCartas.GeneradorDeCartas;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.carta.ValorDeCartaInvalido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class generadorDeCartasTest {
    @Test
    public void test01SeGeneraUnaCartasDelMismoPalo() {
        GeneradorDeCartas generador = new GeneradorDeCartas();
        Corazon corazon = new Corazon();
        List<Carta> cartas = generador.generarCartasPorLimite(corazon,2,2);

        Carta cartaEsperada = new Carta(corazon, 2, 2,1);
        Assertions.assertTrue(cartas.get(0).sonMismoPalo(cartaEsperada));
    }

    @Test
    public void test02SeGeneraCartasDelMismoPaloDesdeEl2Al10() {
        GeneradorDeCartas generador = new GeneradorDeCartas();
        Corazon corazon = new Corazon();
        List<Carta> cartas = generador.generarCartasPorLimite(corazon,2,10);

        Assertions.assertEquals(9,cartas.size());
    }

    @Test
    public void test03SeGeneraUnRangoDeCartasErroneo(){
        GeneradorDeCartas generador = new GeneradorDeCartas();
        Corazon corazon = new Corazon();

        Assertions.assertThrows(ValorDeCartaInvalido.class,()->{generador.generarCartasPorLimite(corazon,2,15);});
    }

    @Test
    public void test05SeGeneranCartasComoParaUnMaso(){
        GeneradorDeCartas generador = new GeneradorDeCartas();

        List<Carta> cartas = generador.generarCartas();

        Assertions.assertEquals(52, cartas.size());
    }
}
