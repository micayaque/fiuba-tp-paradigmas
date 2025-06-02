package edu.fiuba.algo3.controllers.Parser;

import edu.fiuba.algo3.controllers.Parseados.TarotParseado;

import java.util.List;

public class ListaTarotParseado {
    private List<TarotParseado> tarots;

    public List<TarotParseado> getTarots() {
        return tarots;
    }

    public void setTarots(List<TarotParseado> tarot) {
        this.tarots = tarot;
    }
}
