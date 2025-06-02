package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Mano.Mano;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.ManoDeComodines.ManoDeComodines;
import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Palo.Diamante;
import edu.fiuba.algo3.modelo.Palo.Pica;
import edu.fiuba.algo3.modelo.Palo.Trebol;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BalatroTest {



    @Test
    void test01jugadorSeleccionaUnaCartaYJuegaSinComodines(){
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));

        //arrange
        Mazo mazoMockeado = Mockito.mock(Mazo.class);
        Mockito.when(mazoMockeado.generarCartas()).thenReturn(cartas);


        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMockeado,3, comodines);
        //act
        //en estos casos voy a actuar como el jugador
        manoDeLaRonda.seleccionarCarta(cartas.get(2));
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        Assertions.assertEquals(7,puntosObtenidos.calcularPuntaje());

    }

    @Test
    void test02jugadorSeleccionaUnaCartaYJuegaSinComodines(){

        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Pica(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 12,10,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));

        Mazo mazoMockeado = Mockito.mock(Mazo.class);
        Mockito.when(mazoMockeado.generarCartas()).then(invocation -> cartas);


        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMockeado, 3 ,comodines);
        //act
        //en estos casos voy a actuar como el jugador
        manoDeLaRonda.seleccionarCarta(cartas.get(1));
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        Assertions.assertEquals(8,puntosObtenidos);

    }

    @Test
    void test03jugadorSeleccionaDosCartasDelMismoNumeroYJuegaSinComodines(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Pica(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Trebol(), 4,4,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 12,10,1));
        cartas.add( new Carta(new Diamante(), 14,10,1));

        Mazo mazoMockeado = Mockito.mock(Mazo.class);
        Mockito.when(mazoMockeado.generarCartas()).then(invocation -> cartas);


        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMockeado,3,comodines);
        //act
        //en estos casos voy a actuar como el jugador
        manoDeLaRonda.seleccionarCarta(cartas.get(4));
        manoDeLaRonda.seleccionarCarta(cartas.get(6)); //verificar mano porque no se puede seleccionar la posicion 7 //no se puede porque mientras se eligen, se van de la lista
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        Assertions.assertEquals(28,puntosObtenidos.calcularPuntaje());

    }

    @Test
    void test04jugadorSeleccionaDosParesDeCartasMismoNumeroYJuegaSinComodines(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Pica(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Trebol(), 4,4,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 12,10,1));
        cartas.add( new Carta(new Diamante(), 12,10,1));

        Mazo mazoMockeado = Mockito.mock(Mazo.class);
        Mockito.when(mazoMockeado.generarCartas()).then(invocation -> cartas);

        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMockeado, 3, comodines);

        //act
        //en estos casos voy a actuar como el jugador
        manoDeLaRonda.seleccionarCarta(cartas.get(4));
        manoDeLaRonda.seleccionarCarta(cartas.get(6));
        manoDeLaRonda.seleccionarCarta(cartas.get(1));
        manoDeLaRonda.seleccionarCarta(cartas.get(0));
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        Assertions.assertEquals(88,puntosObtenidos.calcularPuntaje());

    }

    @Test
    void test05jugadorSeleccionaEscaleraYJuegaSinComodines(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add( new Carta(new Corazon(), 5,5,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Pica(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Trebol(), 4,4,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 6,6,1));
        cartas.add( new Carta(new Diamante(), 12,10,1));

        Mazo mazoMockeado = Mockito.mock(Mazo.class);
        Mockito.when(mazoMockeado.generarCartas()).then(invocation -> cartas);


        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMockeado, 3 ,comodines);
        //act
        //en estos casos voy a actuar como el jugador
        manoDeLaRonda.seleccionarCarta(cartas.get(5));   //{12,6,5,4,3,2,2,2} -> {12,6,5,4,3,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));   //-> {12,5,4,3,2,2}
        manoDeLaRonda.desSeleccionarCarta(new Carta(new Pica(), 2,2,1)); //{12,5,4,3,2,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(6)); // -> {12,5,4,3,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));  //-> {12,4,3,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(2));  //-> {12,4,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));  //-> {12,2,2}
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        //6+5+4+3+2 de las cartas + 30 de la mano de poker (escalera simple)
        //multiplicado por el multiplicador de la mano de poker 4
        assertEquals(200,puntosObtenidos.calcularPuntaje());

    }

    @Test
    void test06jugadorSeleccionaEscaleraColorYJuegaSinComodines(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add( new Carta(new Corazon(), 5,5,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 4,4,1));
        cartas.add( new Carta(new Pica(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 6,6,1));
        cartas.add( new Carta(new Diamante(), 12,10,1));

        Mazo mazoMock = Mockito.mock(Mazo.class);
        Mockito.when(mazoMock.generarCartas()).then(invocationOnMock -> cartas);

        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMock,3,comodines);
        //act
        //en estos casos voy a actuar como el jugador
        manoDeLaRonda.seleccionarCarta(cartas.get(7));   //{12,6,5,4,3,2,2,2} -> {12,6,5,4,3,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));   //-> {12,5,4,3,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));  //-> {12,4,3,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(0));  //-> {12,4,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));  //-> {12,2,2}
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        //6+5+4+3+2 de las cartas + 100 de la mano de poker (escalera color)
        //multiplicado por el multiplicador de la mano de poker 8
        Assertions.assertEquals(960,puntosObtenidos.calcularPuntaje());

    }

    @Test
    void test07jugadorSeleccionaEscaleraColorYJuegaSinComodines(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        Carta cartaASacar = new Carta(new Pica(), 2,2,1);
        cartas.add( new Carta(new Corazon(), 5,5,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Trebol(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 4,4,1));
        cartas.add( cartaASacar);
        cartas.add( new Carta(new Corazon(), 6,6,1));
        cartas.add( new Carta(new Diamante(), 12,10,1));

        Mazo mazoMockeado = Mockito.mock(Mazo.class);
        Mockito.when(mazoMockeado.generarCartas()).then(invocation -> cartas);


        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMockeado, 3, comodines);
        //act
        //en estos casos voy a actuar como el jugador
        manoDeLaRonda.seleccionarCarta(cartas.get(7));   //{12,6,5,4,3,2,2,2} -> {12,6,5,4,3,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));   //-> {12,5,4,3,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));  //-> {12,4,3,2,2}
        manoDeLaRonda.desSeleccionarCarta(cartaASacar); //-> {12,4,3,2,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(2));  //-> {12,4,2,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(1));  //-> {12,2,2,2}
        manoDeLaRonda.seleccionarCarta(cartas.get(2));  //-> {12,2,2}
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        //6+5+4+3+2 de las cartas + 100 de la mano de poker (escalera color)
        //multiplicado por el multiplicador de la mano de poker 8
        Assertions.assertEquals(960,puntosObtenidos.calcularPuntaje());

    }

    @Test
    void test08jugadorAplicaTarotAManoDePokerSeleccionaUnaCartaYJuegaSinComodines(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Pica(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 12,10,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));


        Tarot tarot = new Tarot("qsy", "aumenta los puntos en 10 y el multi en 2", new Puntaje(10, 2), new Modificador("carta mas alta"));
        /*MazoDeTarots tarots = new MazoDeTarots();
        tarots.agregar(tarot);*/
        Mazo mazoMockeado = Mockito.mock(Mazo.class);
        Mockito.when(mazoMockeado.generarCartas()).then(invocation -> cartas);

        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMockeado, 3, comodines);


        //act
        //en estos casos voy a actuar como el jugador
        manoDeLaRonda.seleccionarCarta(cartas.get(1));
        manoDeLaRonda.aplicarTarot(tarot); //provisorio
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        Assertions.assertEquals(26,puntosObtenidos);

    }

    @Test
    void test09jugadorAplicaTarotACartaDeValor5YSeleccionaCartaMasAltaYJuegaSinComodines(){
        //arrange
        ArrayList<Carta> cartas = new ArrayList<Carta>();
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Pica(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));
        cartas.add( new Carta(new Corazon(), 3,3,1));
        cartas.add( new Carta(new Corazon(), 5,5,1));
        cartas.add( new Carta(new Corazon(), 12,10,1));
        cartas.add( new Carta(new Corazon(), 2,2,1));


        Tarot tarot = new Tarot("qsy", "aumenta los puntos en 10 y el multi en 2", new Puntaje(10, 2), new Modificador("carta"));
        /*MazoDeTarots tarots = new MazoDeTarots();
        tarots.agregar(tarot);*/

        Mazo mazoMockeado = Mockito.mock(Mazo.class);
        Mockito.when(mazoMockeado.generarCartas()).then(invocation -> cartas);


        ManoDeComodines comodines = new ManoDeComodines();
        Mano manoDeLaRonda = new Mano(mazoMockeado, 3, comodines);
        //act
        //en estos casos voy a actuar como el jugador
        tarot.aplicarA(cartas.get(6));
        manoDeLaRonda.seleccionarCarta(cartas.get(6));
        Puntaje puntosObtenidos = manoDeLaRonda.jugarCartas();

        //assert
        Assertions.assertEquals(30,puntosObtenidos);

    }
}
