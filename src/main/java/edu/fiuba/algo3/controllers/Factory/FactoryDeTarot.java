package edu.fiuba.algo3.controllers.Factory;

import edu.fiuba.algo3.controllers.Parser.ParserTarot;
import edu.fiuba.algo3.controllers.Parseados.TarotParseado;
import edu.fiuba.algo3.modelo.Prooveedor.ProveedorDeTarots;
import edu.fiuba.algo3.modelo.Tarot.Tarot;

import java.util.List;

public class FactoryDeTarot implements ProveedorDeTarots {
    private String ruta;
    public FactoryDeTarot(String ruta){
        this.ruta = ruta;
    }

    @Override
    public List<Tarot> generarTarots() {
        List<TarotParseado> tarotsFake = ParserTarot.convertirDeJsonAFakeTarot(this.ruta);
        List<Tarot> tarots = ParserTarot.parsearDeFakeTarotATarot(tarotsFake);
        return tarots;
    }
    public List<Tarot> generarTarots( List<TarotParseado> tarotsFake) {
        List<Tarot> tarots = ParserTarot.parsearDeFakeTarotATarot(tarotsFake);
        return tarots;
    }
}
