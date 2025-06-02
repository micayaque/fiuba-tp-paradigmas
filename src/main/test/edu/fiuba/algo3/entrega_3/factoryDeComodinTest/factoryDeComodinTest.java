package edu.fiuba.algo3.entrega_3.factoryDeComodinTest;


import edu.fiuba.algo3.controllers.Factory.FactoryComodines;
import edu.fiuba.algo3.modelo.ManoDePoker.EscaleraColor;
import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;
import edu.fiuba.algo3.modelo.ManoDePoker.Poker;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class factoryDeComodinTest {
    @Test
    public void test01SeCreanTodosLosComodinesYSeCuentan(){
        FactoryComodines factoryComodines = new FactoryComodines("src/main/resources/comodines.json");

        List<Comodin> listaComodines = factoryComodines.generarComodines();

        Assertions.assertEquals(38,listaComodines.size());
    }
    @Test
    public void test02SeCreanTodosLosComodinesYSeUsaElComodinPoderoso(){
        FactoryComodines factoryComodines = new FactoryComodines("src/main/resources/comodines.json");

        List<Comodin> listaComodines = factoryComodines.generarComodines();

        Comodin comodinDePrueba = listaComodines.get(8);

        ManoDePoker poker = new Poker();

        comodinDePrueba.aplicarA(poker);

        Assertions.assertEquals(2520,poker.calcularPuntaje());
    }

}
