package edu.fiuba.algo3.controllers.Factory;

import edu.fiuba.algo3.controllers.Parseados.ComodinParseado;
import edu.fiuba.algo3.controllers.Parseados.TarotParseado;
import edu.fiuba.algo3.controllers.Parser.ParserComodin;
import edu.fiuba.algo3.controllers.Parser.ParserTarot;
import edu.fiuba.algo3.modelo.Prooveedor.ProveedorDeComodines;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.comodin.Comodin;

import java.util.List;

public class FactoryComodines implements ProveedorDeComodines {
    private String ruta;
    public FactoryComodines(String ruta){
        this.ruta = ruta;
    }

    @Override
    public List<Comodin> generarComodines() {
        List<ComodinParseado> comodinFake = ParserComodin.convertirDeJsonAFakeComodin(this.ruta);
        List<Comodin> comodines = ParserComodin.parsearDeFakeComodinAComodin(comodinFake);
        return comodines;
    }

    public List<Comodin> generarComodines(List<ComodinParseado> fakeComodines){
        List<Comodin> comodines = ParserComodin.parsearDeFakeComodinAComodin(fakeComodines);
        return comodines;
    }
}
