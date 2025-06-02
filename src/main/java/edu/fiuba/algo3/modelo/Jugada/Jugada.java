package edu.fiuba.algo3.modelo.Jugada;

import edu.fiuba.algo3.modelo.ManoDePoker.CartaMasAlta;
import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.Ordenador.OrdenadorDeCartas;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;
import edu.fiuba.algo3.modelo.definidorDeManoDePoker.DefinidorDeManoDePoker;

import java.util.ArrayList;


public class Jugada {

    private ArrayList<Carta> cartas;
    private ManoDePoker manoDePoker = new CartaMasAlta();
    private DefinidorDeManoDePoker definidor;

    public Jugada(){
        this.cartas = new ArrayList<Carta>();
        this.definidor = new DefinidorDeManoDePoker();
    }

    public ManoDePoker seleccionar(Carta carta){
        if (this.cartas.size() >= 5) {
            throw new IllegalStateException("Ya se han seleccionado las cartas máximas permitidas (5 cartas).");
        }
        this.cartas.add(carta);
        OrdenadorDeCartas.ordenarCartas(this.cartas);
        definirManoDePoker();
        return this.manoDePoker;
    }

    public Carta deseleccionar(Carta carta){
        this.cartas.remove(carta);  //devuleve la carta para que la mano la vuelva a guadar en su array
        this.definirManoDePoker();
        return carta;
    }


    private void definirManoDePoker(){
        this.manoDePoker = this.definidor.definirManoDePoker(this.cartas);
    }

    public int cantidadDeCartas(){
        return this.cartas.size();
    }

    public ManoDePoker jugar(){
        this.manoDePoker.jugar(this.cartas);
        this.cartas.clear();
        return this.manoDePoker;
    }

    public void descartar(Mazo mazo){
        mazo.reponer(this.cartas);
        this.cartas.clear();
    }

    public boolean tieneMano(ManoDePoker otraManoDePoker){
        return this.manoDePoker.equals(otraManoDePoker);
    }

    public void aplicarTarotAMano(Tarot tarot){
        this.definidor.aplicarTarotAMano(tarot);
    }

    public Puntaje puntajeActual() {
        return this.manoDePoker.calcularPuntaje();
    }
}
