package edu.fiuba.algo3.modelo.ronda;
import edu.fiuba.algo3.controllers.Parseados.ComodinParseado;
import  edu.fiuba.algo3.modelo.carta.Carta;
import  edu.fiuba.algo3.modelo.comodin.Comodin;
import  edu.fiuba.algo3.modelo.Tarot.Tarot;
import java.util.List;


public class Tienda {
    private List<Comodin> comodines;
    private List<Tarot> tarots;
    private List<Carta> cartas;

    public Tienda(List<Tarot> tarots, List<Comodin> comodines, List<Carta> cartas) {
        this.comodines = comodines;
        this.tarots = tarots;
        this.cartas = cartas;
    }
    public List<Comodin> obtenerComodines() {
        return comodines;
    }

    public List<Tarot> obtenerTarots() {
        return tarots;
    }

    public List<Carta> obtenerCartas() {return cartas;}

    public void seleccionar(Comodin comodinSeleccionado) {
        comodines.removeIf(comodin -> comodin.equals(comodinSeleccionado));
    }

    public void seleccionar(Tarot tarotSeleccionado) {
      tarots.removeIf(tarot -> tarot.equals(tarotSeleccionado));
    }

    public void seleccionar(Carta cartaSeleccionado) {
      cartas.removeIf(carta -> carta.equals(cartaSeleccionado));
    }


}
