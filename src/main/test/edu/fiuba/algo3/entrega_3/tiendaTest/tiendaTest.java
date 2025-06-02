package edu.fiuba.algo3.entrega_3.tiendaTest;

import edu.fiuba.algo3.controllers.Factory.FactoryComodines;
import edu.fiuba.algo3.controllers.Factory.FactoryDeMazo;
import edu.fiuba.algo3.controllers.Factory.FactoryDeTarot;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.ronda.Tienda;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class tiendaTest {
    FactoryComodines factoryComodines;
    FactoryDeMazo factoryDeMazo;
    FactoryDeTarot factoryDeTarot;
    List<Tarot> tarots;
    List<Carta> cartas;
    List<Comodin> comodines;
    List<Comodin> mockComodines;
    List<Tarot> mockTarots;
    List<Carta> mockCartas;

    @BeforeEach
    public void setUp() {
        factoryComodines = new FactoryComodines("src/main/resources/comodines.json");
        factoryDeMazo = new FactoryDeMazo("src/main/resources/mazo.json");
        factoryDeTarot = new FactoryDeTarot("src/main/resources/tarots.json");

        tarots = factoryDeTarot.generarTarots();
        cartas = factoryDeMazo.generarCartas();
        comodines = factoryComodines.generarComodines();

        // Crear sublistas de prueba con 8 elementos
        mockComodines = comodines.subList(0, 8);
        mockTarots = tarots.subList(0, 8);
        mockCartas = cartas.subList(0, 8);
    }

    @Test
    public void test01SeCreaUnaTiendaYSeLePidenLosComodines() {
        Tienda tienda = new Tienda(tarots, mockComodines, cartas);

        List<Comodin> comodinesEnTienda = tienda.obtenerComodines();

        Assertions.assertEquals(8, comodinesEnTienda.size());
    }

    @Test
    public void test02SeCreaUnaTiendaYSeLePidenLosTarots() {
        Tienda tienda = new Tienda(mockTarots, comodines, cartas);

        List<Tarot> tarotsEnTienda = tienda.obtenerTarots();

        Assertions.assertEquals(8, tarotsEnTienda.size());
    }

    @Test
    public void test03SeCreaUnaTiendaYSeLePidenLasCartas() {
        Tienda tienda = new Tienda(tarots, comodines, mockCartas);

        List<Carta> cartasEnTienda = tienda.obtenerCartas();

        Assertions.assertEquals(8, cartasEnTienda.size());
    }
}