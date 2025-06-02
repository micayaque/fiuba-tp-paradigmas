package edu.fiuba.algo3.controllers.Parseados;
import java.util.List;

public class TiendaParseado {
    private List<ComodinParseado> comodines;
    private List<TarotParseado> tarots;
    private  CartaParseada carta;

    public List<ComodinParseado> getComodines() {
        return comodines;
    }

    public void setComodines(List<ComodinParseado> comodines) {
        this.comodines = comodines;
    }

    public List<TarotParseado> getTarots() {
        return tarots;
    }

    public void setTarots(List<TarotParseado> tarots) {
        this.tarots = tarots;
    }

    public CartaParseada  getCarta() {
        return carta;
    }

    public void setCarta(CartaParseada carta) {
        this.carta = carta;
    }
}
