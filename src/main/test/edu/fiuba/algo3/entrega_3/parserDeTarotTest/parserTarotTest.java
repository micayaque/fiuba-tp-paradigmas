package edu.fiuba.algo3.entrega_3.parserDeTarotTest;

import com.tngtech.archunit.core.importer.resolvers.ClassResolver;
import edu.fiuba.algo3.controllers.Factory.FactoryDeTarot;
import edu.fiuba.algo3.modelo.ManoDePoker.DoblePar;
import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.ModificableEquivocadoException;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class parserTarotTest {
    @Test
    public void test01SeGeneranTodosLosTarotYSeCuentan(){
        FactoryDeTarot factory = new FactoryDeTarot("src/main/resources/tarots.json");

        List<Tarot> listaTarot = factory.generarTarots();

        Assertions.assertEquals(15, listaTarot.size());
    }

    @Test
    public void test02SeValidaElCorrectoFuncionamientoDelHidrofante(){
        FactoryDeTarot factory = new FactoryDeTarot("src/main/resources/tarots.json");
        List<Tarot> listaTarot = factory.generarTarots();
        Carta cartaModificada = new Carta(new Corazon(), 5, 5, 1);


        Tarot hidrofante = listaTarot.get(5);

        hidrofante.aplicarA(cartaModificada);
        Puntaje puntajeModificada = cartaModificada.calcularPuntaje();

        Assertions.assertEquals(40, puntajeModificada.calcularPuntaje());

    }

    @Test
    public void test03SeChequeaElCorrectoFuncionamientoDeLaSumaSacerdotisa(){
        FactoryDeTarot factory = new FactoryDeTarot("src/main/resources/tarots.json");
        List<Tarot> listaTarot = factory.generarTarots();
        ManoDePoker doblePar = new DoblePar();


        Tarot sumaSacerdotisa = listaTarot.get(2);

        sumaSacerdotisa.aplicarA(doblePar);


        Assertions.assertEquals(40, doblePar.calcularPuntaje());
    }

    @Test
    public void test04ElHidrofanteNoSePuedeAplicarEnElDoblePar(){
        FactoryDeTarot factory = new FactoryDeTarot("src/main/resources/tarots.json");
        List<Tarot> listaTarot = factory.generarTarots();
        ManoDePoker doblePar = new DoblePar();

        Tarot hidrofante = listaTarot.get(5);

        Assertions.assertThrows(ModificableEquivocadoException.class, () -> {hidrofante.aplicarA(doblePar);});
    }
}
