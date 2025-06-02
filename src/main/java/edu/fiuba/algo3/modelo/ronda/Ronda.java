package edu.fiuba.algo3.modelo.ronda;
import edu.fiuba.algo3.modelo.Jugador.Jugador;
import edu.fiuba.algo3.modelo.Mano.Mano;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.ManoDeComodines.ManoDeComodines;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.comodin.Comodin;

import java.util.List;

public class Ronda {
    private Jugador jugador;
    private Tienda tienda;
    private Mazo mazo;
    private int nro;
    private int manos;
    private int descartes;
    private int puntajeASuperar;
    private int puntajeDeRonda;
    private int manosJugadas;
    public Ronda(Tienda tienda, int nro, int manos, int descartes, int puntajeASuperar) {
        this.tienda = tienda;
        this.nro = nro;
        this.manos = manos;
        this.descartes = descartes;
        this.puntajeASuperar = puntajeASuperar;
        this.puntajeDeRonda = 0;
        this.manosJugadas = 0;
    }

    public Tienda verTienda() {
        return tienda;
    }


    public List<Carta> mostrarCartasDeManos() {
        return this.jugador.verCartasEnMano();
    }

    public void empezarRonda(Jugador jugador, Mazo mazo) {
        this.mazo = mazo;
        this.jugador = jugador;
        this.jugador.asignarMano(this.mazo, this.descartes);
    }
    private void arrancarNuevaMano() {
        this.jugador.asignarMano(this.mazo, this.descartes);

    }

    public Puntaje seleccionar(Carta carta) {
        return this.jugador.seleccionar(carta);
    }


    public Puntaje jugar() {
        Puntaje puntajeActual = new Puntaje(0, 1);
        if(manosJugadas <= this.manos) {
            puntajeActual = this.jugador.jugarMano();
            this.arrancarNuevaMano();
            puntajeDeRonda += puntajeActual.calcularPuntaje();
            manosJugadas++;

        } else{
            throw new ManosExedidas();
        }
        return puntajeActual;
    }

    public boolean seGanoLaRonda() {
        return this.puntajeDeRonda >= this.puntajeASuperar && this.manosJugadas == this.manos;
    }

    public boolean sePerdioLaRonda(){
        return this.puntajeDeRonda <= this.puntajeASuperar && this.manosJugadas == this.manos;
    }

    public void comprarComodin(Comodin comodin) {
        this.jugador.comprarComodin(comodin);
    }
    public int obtenerPuntajeDeRonda(){
        return this.puntajeDeRonda;
    }

    public void comprarTarot(Tarot tarot) {
        jugador.comprarTarots(tarot);
    }

    public void aplicarTarotAMano(Tarot tarot) {
        jugador.aplicarTarotAMano(tarot);
    }

    public void aplicarTarotACarta(Carta carta, Tarot tarot) {
        jugador.aplicarTarotACarta(tarot, carta);
    }

    public Puntaje deSeleccionarUnaCarta(Carta carta) {
        return jugador.desSeleccionarUnaCarta(carta);
    }

    public void descartar() {
        jugador.descartar();
    }


    public int verPuntajeASuperar() {
        return this.puntajeASuperar;
    }

    public int verDescartes() {
        return this.descartes;
    }

    public int verManos() {
        return this.manos;
    }

    public int verNro() {
        return this.nro;
    }

    public int verPuntajeDeRonda() {
        return this.puntajeDeRonda;
    }
}
