package edu.fiuba.algo3.entrega_2.OrdenadorDeCartasTest;

import edu.fiuba.algo3.modelo.Ordenador.OrdenadorDeCartas;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Palo.Diamante;
import edu.fiuba.algo3.modelo.Palo.Trebol;
import edu.fiuba.algo3.modelo.carta.Carta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ordenadorDeCartasTest {
    Carta carta1;
    Carta carta2;
    Carta carta3;
    Carta carta4;

    List<Carta> cartasOrdenadas;
    @BeforeEach
    void setUp(){
        carta1 = new Carta(new Corazon(), 2, 2, 1);
        carta2 = new Carta(new Diamante(),3,3,1);
        carta3 = new Carta(new Trebol(), 10,10,1);
        carta4 = new Carta(new Corazon(),3, 3,1);cartasOrdenadas = new ArrayList<>();
        cartasOrdenadas.add(carta3);
        cartasOrdenadas.add(carta4);
        cartasOrdenadas.add(carta2);
        cartasOrdenadas.add(carta1);
    }
    @Test
    public void test01comparamosDosListasOrdenadasPorMano(){
        List<Carta> cartasDesOrdenadas = new ArrayList<>();
        cartasDesOrdenadas.add(carta1);
        cartasDesOrdenadas.add(carta2);
        cartasDesOrdenadas.add(carta3);
        cartasDesOrdenadas.add(carta4);

        OrdenadorDeCartas.ordenarCartas(cartasDesOrdenadas);

        Assertions.assertEquals(cartasOrdenadas, cartasDesOrdenadas);
    }
}
