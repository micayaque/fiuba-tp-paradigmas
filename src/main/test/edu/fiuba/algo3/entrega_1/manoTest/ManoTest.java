package edu.fiuba.algo3.entrega_1.manoTest;

import edu.fiuba.algo3.modelo.GeneradorDeCartas.GeneradorDeCartas;
import edu.fiuba.algo3.modelo.Mano.Mano;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.ManoDeComodines.ManoDeComodines;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Palo.Diamante;
import edu.fiuba.algo3.modelo.Palo.Trebol;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.controllers.Factory.FactoryDeMazo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ManoTest {
    GeneradorDeCartas generadorDeCartas;
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
    public void test01unaManoSeCreaConUnTamanioDe8cartas(){
        //arrange
        Mazo mazo = new Mazo(new FactoryDeMazo("src/main/resources/mazo.json"));
        Mano mano = new Mano(mazo,3, new ManoDeComodines());

    //act assert
        Assertions.assertEquals(true, mano.esManoCompleta());
    }

}
