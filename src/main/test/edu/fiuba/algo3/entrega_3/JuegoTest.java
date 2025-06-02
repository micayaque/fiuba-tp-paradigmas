package edu.fiuba.algo3.entrega_3;

import edu.fiuba.algo3.modelo.Balatro.Balatro;
import edu.fiuba.algo3.modelo.Balatro.Derrota;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.ronda.Tienda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;



public class JuegoTest {
    // todo: que las cartas sssno desaparezcanm sino que realmente vuelvan al mazo
    // descartar
    // comprar tarot
    // comprar comodin
    // comprar carta
    // jugar
    Balatro balatro;
    @BeforeEach
    public void setUp() throws IOException{
       balatro = Balatro.juego();
      balatro.inicializadorDeBalatro("src/main/resources/balatro.json", "src/main/resources/mazo.json", "src/main/resources/tarots.json", "src/main/resources/comodines.json", "el pepe");
    }

    @Test
    void test01EljuegoMuestraLasCartasEnMano()  {

        List<Carta> cartas = balatro.mostrarCartasDeLaMano();

        Assertions.assertEquals(8, cartas.size());
    }

    @Test
    void test02JuegoMuestraLosComodinesDeLaTiendaDeLaRondaActual(){
        Tienda tienda = balatro.mostrarTienda();

        List<Tarot> tarots = tienda.obtenerTarots();

        Assertions.assertEquals(2, tarots.size());
    }

    @Test
    void test03JuegoSeleccionaUnaCartaYMuestraElPuntaje(){
        List<Carta> cartas = balatro.mostrarCartasDeLaMano();

        Carta carta = cartas.get(0);

        Puntaje puntaje = balatro.seleccionar(carta);

        Assertions.assertEquals(5, puntaje.calcularPuntaje());
    }

    @Test
    void test04JuegoDeseleccionaUnaCarta(){
        List<Carta> cartas = balatro.mostrarCartasDeLaMano();

        Carta carta = cartas.get(0);

        Puntaje puntaje = balatro.seleccionar(carta);

        puntaje = balatro.deseleccionarCarta(carta);

        Assertions.assertEquals(5, puntaje.calcularPuntaje());
    }

    @Test
    public void test05JuegoHaceUnDescarteYVerificoQueSigaTeniendo8Cartas() {
        List<Carta> cartas = balatro.mostrarCartasDeLaMano();
        Carta desCartable1 = cartas.get(0);
        Carta desCartable2 = cartas.get(1);
        Carta desCartable3 = cartas.get(2);

        balatro.seleccionar(desCartable1);
        balatro.seleccionar(desCartable2);
        balatro.seleccionar(desCartable3);

        balatro.descartar();

        Assertions.assertEquals(8, balatro.mostrarCartasDeLaMano().size());
    }

    @Test
    public void test06JuegoHaceQueElJugadorJuegueUnaRondaCompleta(){
        List<Carta> cartas = balatro.mostrarCartasDeLaMano();

        Carta carta = cartas.get(0);
        Carta otraCarta = cartas.get(1);
        Carta tercerCarta = cartas.get(2);
        //mano 1
        balatro.seleccionar(carta);
        balatro.seleccionar(otraCarta);
        balatro.seleccionar(tercerCarta);


        int puntaje = balatro.jugar();

        //mano 2
        cartas = balatro.mostrarCartasDeLaMano();


        carta = cartas.get(0);
        otraCarta = cartas.get(1);
        tercerCarta = cartas.get(2);
        balatro.seleccionar(carta);
        balatro.seleccionar(otraCarta);
        balatro.seleccionar(tercerCarta);

        int puntaje2 = balatro.jugar();

        //mano 3

        cartas = balatro.mostrarCartasDeLaMano();
        carta = cartas.get(0);
        otraCarta = cartas.get(1);
        tercerCarta = cartas.get(2);
        balatro.seleccionar(carta);
        balatro.seleccionar(otraCarta);
        balatro.seleccionar(tercerCarta);

        Assertions.assertThrows(Derrota.class,  () ->{ int puntaje3 = balatro.jugar();});


    }

}
