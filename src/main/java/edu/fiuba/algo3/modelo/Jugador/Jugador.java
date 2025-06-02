package edu.fiuba.algo3.modelo.Jugador;

import edu.fiuba.algo3.modelo.Mano.Mano;
import edu.fiuba.algo3.modelo.ManoDeComodines.ManoDeComodines;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.comodin.Comodin;
import edu.fiuba.algo3.modelo.manoDeTarots.ManoDeTarots;

import java.util.List;

public class Jugador {
    private static Jugador jugador;
    private String nombre;
    private Mano mano;
    private Puntaje puntajeTotal;
    private ManoDeTarots manoDeTarots;
    private ManoDeComodines manoDeComodines;
    private Jugador(String nombre) {
        this.nombre = nombre;
        this.puntajeTotal = new Puntaje(0,1);
        this.manoDeComodines = new ManoDeComodines();
        this.manoDeTarots = new ManoDeTarots();
    }

    public static Jugador CrearJugador(String nombre) {
        if(jugador == null) {
            jugador = new Jugador(nombre);
        }return jugador;
    }

    public Puntaje seleccionar(Carta carta) {
        return this.mano.seleccionarCarta(carta);
    }


    public void asignarMano(Mazo mazo, int descartes) {
        this.mano = new Mano(mazo, descartes, manoDeComodines);
    }

    public List<Carta> verCartasEnMano(){
        return List.copyOf(mano.verCartasEnMano());
    }


    public Mano mostrarMano() {return this.mano;}



    public Puntaje jugarMano() {
        Puntaje puntaje = mano.jugarCartas();
        this.puntajeTotal = this.puntajeTotal.sumarConPuntaje(puntaje);
        return puntaje;
    }

    public void comprarTarots(Tarot tarot) {
        manoDeTarots.guardar(tarot);
    }

    public void asignarTarots(ManoDeTarots manoDeTarots) {
        this.manoDeTarots = manoDeTarots;
    }


    public void aplicarTarotAMano( Tarot tarot) {
            Tarot tarotJugado = manoDeTarots.usarTarot(tarot);
           mano.aplicarTarot(tarotJugado);
    }

    public void aplicarTarotACarta(Tarot tarot, Carta carta) {
        Tarot tarotAUsar = manoDeTarots.usarTarot(tarot);
        mano.aplicarTarotACarta(tarotAUsar, carta);
    }

    public void descartar() {
        mano.descartarCartas();
    }

    public int obtenerPuntajeFinal(){
        return puntajeTotal.calcularPuntaje();
    }

    public void comprarComodin(Comodin comodin) {
        this.manoDeComodines.guardar(comodin);
    }

    public Puntaje desSeleccionarUnaCarta(Carta carta) {
        return mano.desSeleccionarCarta(carta);
    }
}
