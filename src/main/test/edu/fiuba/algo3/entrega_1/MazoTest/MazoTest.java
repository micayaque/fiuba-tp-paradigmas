package edu.fiuba.algo3.entrega_1.MazoTest;
import edu.fiuba.algo3.modelo.GeneradorDeCartas.GeneradorDeCartas;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Palo.Diamante;
import edu.fiuba.algo3.modelo.Palo.Trebol;
import edu.fiuba.algo3.modelo.carta.Carta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class MazoTest {
    GeneradorDeCartas generadorDeCartas;
    @BeforeEach
    void setUp(){
        generadorDeCartas = new GeneradorDeCartas();
    }
    @Test

    public void test01ElMazoDeCartasSeCreaConLaCantidadDeCartasCorrectas(){
        Mazo maso = new Mazo(generadorDeCartas);

        // Act y assert
        Assertions.assertEquals(52, maso.obtenerCantidadDeCartas());
    }
    @Test
    public void test02ElMazoMePuedeRepartirUnaCantidadDeOchoCartasCorrectamente(){
        //arrange
        int tamanioMano = 8;
        List<Carta> cartasMano;
        Mazo mazo = new Mazo(generadorDeCartas);
        //act
        cartasMano = mazo.repartirCartas();
        //Assert
        Assertions.assertEquals(tamanioMano, cartasMano.size());
    }

    @Test

    public void test03ElmazoMeReparteUnaCantidadCorrectaDeCartasTeniendoUnaManoDe3cartas(){
        //arrange
        Mazo mazo = new Mazo(generadorDeCartas);
        int tamanioMano = 8;
        List<Carta> cartasMano = new ArrayList<>();
        Carta carta1 = new Carta(new Corazon(), 2, 2, 1);
        Carta carta2 = new Carta(new Diamante(),3,3,1);
        Carta carta3 = new Carta(new Trebol(), 10,10,1);

        cartasMano.add(carta1);
        cartasMano.add(carta2);
        cartasMano.add(carta3);

        //act
        mazo.reponer(cartasMano);

        //Asssert
        Assertions.assertEquals(tamanioMano , cartasMano.size());
    }
}
