package edu.fiuba.algo3.entrega_3.modificableTarotTest;

import edu.fiuba.algo3.modelo.ManoDePoker.Trio;
import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Palo.Corazon;
import edu.fiuba.algo3.modelo.carta.Carta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class modificableTarotTest {
    @Test
    public void test01SeDeseaSaberSiSePuedeModificarUnaCarta(){
        Carta carta = new Carta(new Corazon(), 9, 9,1);
        // estaria bueno que modificador reciba un modificable y se encargue de validar puede aplicarse
        Modificador modificador = new Modificador("Carta");

        Assertions.assertTrue(modificador.modificar(carta));
    }

    @Test
    public void test02SeDeseaSaberSiSePuedeModificarUnTrio(){
        Trio trio = new Trio();

        Modificador modificador = new Modificador("trio");

        Assertions.assertTrue(modificador.modificar(trio));

    }
}
