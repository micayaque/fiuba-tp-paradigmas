package edu.fiuba.algo3.entrega_1.definidorDeManoDePokerTest;

import edu.fiuba.algo3.modelo.ManoDePoker.*;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Palo.Pica;
import edu.fiuba.algo3.modelo.Palo.Trebol;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.definidorDeManoDePoker.DefinidorDeManoDePoker;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinidorDeManoDePokerTest {
    @Test
    void test01seSeleccionaUnaEscaleraReal(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add(new Carta(new Corazon(),14,10,1));
        //Palo palo, int valor, int puntajeBase, int multiplicador
        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Pica(),12,10,1));
        cartas.add(new Carta(new Corazon(),11,10,1));
        cartas.add(new Carta(new Trebol(),10,10,1));

        ManoDePoker manoDePokerEsperada = new EscaleraReal();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }
    @Test
    void test02seSeleccionaUnaEscaleraColor(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),12,10,1));
        cartas.add(new Carta(new Corazon(),11,10,1));
        cartas.add(new Carta(new Corazon(),10,10,1));
        cartas.add(new Carta(new Corazon(),9,9,1));


        ManoDePoker manoDePokerEsperada = new EscaleraColor();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }
    @Test
    void test03seSeleccionaUnaEscaleraSimple(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),12,10,1));
        cartas.add(new Carta(new Corazon(),11,10,1));
        cartas.add(new Carta(new Corazon(),10,10,1));
        cartas.add(new Carta(new Pica(),9,9,1));


        ManoDePoker manoDePokerEsperada = new EscaleraSimple();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test04seSeleccionaUnaManoDePoker(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),11,10,1));
        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Pica(),13,10,1));

        ManoDePoker manoDePokerEsperada = new Poker();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test05seSeleccionaUnaManoFullHouse(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),11,10,1));
        cartas.add(new Carta(new Corazon(),11,10,1));
        cartas.add(new Carta(new Pica(),13,10,1));

        ManoDePoker manoDePokerEsperada = new FullHouse();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test06seSeleccionaUnaManoDeColor(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Pica(),13,10,1));
        cartas.add(new Carta(new Pica(),13,10,1));
        cartas.add(new Carta(new Pica(),11,10,1));
        cartas.add(new Carta(new Pica(),2,2,1));
        cartas.add(new Carta(new Pica(),13,10,1));

        ManoDePoker manoDePokerEsperada = new Color();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test07seSeleccionaUnTrio(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Trebol(),4,4,1));
        cartas.add(new Carta(new Corazon(),2,2,1));
        cartas.add(new Carta(new Pica(),4,4,1));

        ManoDePoker manoDePokerEsperada = new Trio();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test08seSeleccionanDoblesPares(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),2,2,1));
        cartas.add(new Carta(new Corazon(),11,10,1));
        cartas.add(new Carta(new Corazon(),2,2,1));
        cartas.add(new Carta(new Pica(),13,10,1));

        ManoDePoker manoDePokerEsperada = new DoblePar();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test09seSeleccionaUnaManoPar(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Corazon(),6,6,1));
        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Pica(),2,2,1));

        ManoDePoker manoDePokerEsperada = new Par();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test10seSeleccionaUnaManoDeCartaMasAlta(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Corazon(),8,8,1));
        cartas.add(new Carta(new Corazon(),3,3,1));
        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Pica(),2,2,1));

        ManoDePoker manoDePokerEsperada = new CartaMasAlta();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test11seSeleccionaUnaManoDeCartaMasAltaConUnaSolaCarta(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),4,4,1));

        ManoDePoker manoDePokerEsperada = new CartaMasAlta();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test12seSeleccionaUnaManoDeCartaMasAltaConDosCartasDeDistintoNumero(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Corazon(),8,8,1));

        ManoDePoker manoDePokerEsperada = new CartaMasAlta();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test13seSeleccionaUnaManoDeCartaMasAltaConTresCartasDistintas(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Corazon(),8,8,1));
        cartas.add(new Carta(new Corazon(),3,3,1));

        ManoDePoker manoDePokerEsperada = new CartaMasAlta();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test14seSeleccionaUnaManoDeCartaMasAltaCon4CartasDistintas(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Corazon(),8,8,1));
        cartas.add(new Carta(new Corazon(),3,3,1));
        cartas.add(new Carta(new Corazon(),13,10,1));

        ManoDePoker manoDePokerEsperada = new CartaMasAlta();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test15seSeleccionanDoblesParesConCuatroCartas(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),2,2,1));
        cartas.add(new Carta(new Corazon(),2,2,1));
        cartas.add(new Carta(new Pica(),13,10,1));

        ManoDePoker manoDePokerEsperada = new DoblePar();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test16seSeleccionaUnaManoParCon4Cartas(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Corazon(),6,6,1));
        cartas.add(new Carta(new Corazon(),13,10,1));

        ManoDePoker manoDePokerEsperada = new Par();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test17seSeleccionaUnaManoParCon3Cartas(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Pica(),2,2,1));

        ManoDePoker manoDePokerEsperada = new Par();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test18seSeleccionaUnaManoParCon2Cartas(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),13,10,1));
        cartas.add(new Carta(new Trebol(),13,10,1));

        ManoDePoker manoDePokerEsperada = new Par();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test19seSeleccionaUnTrioCon3Cartas(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Trebol(),4,4,1));
        cartas.add(new Carta(new Pica(),4,4,1));

        ManoDePoker manoDePokerEsperada = new Trio();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }

    @Test
    void test20seSeleccionaUnTrioCon4Cartas(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();

        cartas.add(new Carta(new Corazon(),4,4,1));
        cartas.add(new Carta(new Trebol(),4,4,1));
        cartas.add(new Carta(new Corazon(),2,2,1));
        cartas.add(new Carta(new Pica(),4,4,1));

        ManoDePoker manoDePokerEsperada = new Trio();
        DefinidorDeManoDePoker definidor = new DefinidorDeManoDePoker();

        //act
        ManoDePoker manoDePokerObtenida = definidor.definirManoDePoker(cartas);

        //assert
        assertEquals(manoDePokerEsperada,manoDePokerObtenida);
    }



}
