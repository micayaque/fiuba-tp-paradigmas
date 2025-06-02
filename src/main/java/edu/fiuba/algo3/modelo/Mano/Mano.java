package edu.fiuba.algo3.modelo.Mano;

import edu.fiuba.algo3.modelo.Jugada.Jugada;
import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.ManoDeComodines.ManoDeComodines;
import edu.fiuba.algo3.modelo.Ordenador.OrdenadorDeCartas;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;

import java.util.ArrayList;
import java.util.List;
public class Mano {

    private Mazo mazo;
    private List<Carta> cartas;
    private Jugada jugada = new Jugada();
    private int descartes;
    private ManoDeComodines comodines;


    public Mano(Mazo mazo,int descartes, ManoDeComodines comodines) {
        this.comodines = comodines;
        this.mazo = mazo;
        this.cartas = mazo.generarCartas();
        this.ordenarMano();
        this.descartes = descartes;
    }


    public boolean esManoCompleta() {
        return cartas.size() == 8;
    }


    private void ordenarMano(){
        OrdenadorDeCartas.ordenarCartas(this.cartas);
    }


    public List<Carta> verCartasEnMano(){
        return cartas;
    }

    public Puntaje seleccionarCarta(Carta carta) {
        if (this.cartas.contains(carta)) {
            Carta seleccionada = this.cartas.get(this.cartas.indexOf(carta));
            jugada.seleccionar(seleccionada);
            List<Carta> cartasAReponer = new ArrayList<>();
            cartasAReponer.add(seleccionada);
            this.cartas.remove(carta);
            mazo.reponer(cartasAReponer);

        } else {
            throw new CartaNoEncontrada();
        }
        return jugada.puntajeActual();
    }

    public Puntaje desSeleccionarCarta(Carta carta){
        this.cartas.add(jugada.deseleccionar(carta));
        this.ordenarMano();
        return jugada.puntajeActual();
    }


    public Puntaje jugarCartas(){
        ManoDePoker manoDePoker = jugada.jugar();  //se juegan las cartas seleccionadas y devuelve los puntos obtenidos
        comodines.aplicarA(manoDePoker);
        return manoDePoker.calcularPuntaje();
    }


    public void descartarCartas(){

        this.descartes--;
        jugada.descartar(this.mazo);
        this.cartas = this.mazo.repartirCartas();

        //comodines.actualizarPorDescarte();
    }
    //este le aplica a la mano que le corresponde
    public void aplicarTarot(Tarot tarot){
        this.jugada.aplicarTarotAMano(tarot);
    }

    public  void aplicarTarotACarta(Tarot tarot, Carta carta) {
        Carta cartaTaroteada;
        if(this.cartas.contains(carta)){
            cartaTaroteada = this.cartas.get(this.cartas.indexOf(carta));
            tarot.aplicarA(cartaTaroteada);
        }else{
            throw new CartaNoEncontrada();
        }
    }
}