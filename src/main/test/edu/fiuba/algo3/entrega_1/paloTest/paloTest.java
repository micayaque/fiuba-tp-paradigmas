package edu.fiuba.algo3.entrega_1.paloTest;

import edu.fiuba.algo3.modelo.Palo.*;
import org.junit.jupiter.api.Test;

public class paloTest {
    @Test
    public void test01UnCorazonSeComparaConUnTrebol(){
        Palo corazon = new Corazon();
        Palo trebol = new Trebol();

        Boolean comparacion = corazon.sonMismoPalo(trebol);

        assert !comparacion;
    }
    @Test
    public void test02UnCorazonSeComparaConOtroCorazon(){
        Palo corazon = new Corazon();
        Palo corazon2 = new Corazon();

        Boolean comparacion = corazon.sonMismoPalo(corazon2);
        assert comparacion;
    }

    @Test
    public void test03UnDiamanteSeSeComparaConPicas(){
        Palo diamante = new Diamante();
        Palo pica = new Pica();

        Boolean comparacion = diamante.sonMismoPalo(pica);

        assert !comparacion;
    }
}
