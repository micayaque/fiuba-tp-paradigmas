package edu.fiuba.algo3.modelo.definidorDeManoDePoker;


import edu.fiuba.algo3.modelo.ManoDePoker.*;
import edu.fiuba.algo3.modelo.Palo.Pica;
import edu.fiuba.algo3.modelo.Tarot.Tarot;
import edu.fiuba.algo3.modelo.carta.Carta;

import java.util.ArrayList;
import java.util.Collections;

public class DefinidorDeManoDePoker {
    //provisoria, va a tener un array de manos de poker y va a aplicar el patron chain of responsability
    private ArrayList<Carta> cartas;
    private int cantidadDeCartas;
    private Carta as;

    private ArrayList<ManoDePoker> manosDePoker;

    public DefinidorDeManoDePoker(){
        this.manosDePoker = new ArrayList<ManoDePoker>();
        this.manosDePoker.add(new CartaMasAlta());
        this.manosDePoker.add(new Color());
        this.manosDePoker.add(new DoblePar());
        this.manosDePoker.add(new EscaleraColor());
        this.manosDePoker.add(new EscaleraReal());
        this.manosDePoker.add(new EscaleraSimple());
        this.manosDePoker.add(new FullHouse());
        this.manosDePoker.add(new Par());
        this.manosDePoker.add(new Poker());
        this.manosDePoker.add(new Trio());
        this.as = new Carta(new Pica(),14,10,1);
    }

   /* public DefinidorDeManoDePoker(){
        this.as = new Carta(new Pica(),14,10,1);
    }*/
/*
    public ManoDePoker definirManoDePoker(String nombreDeLaMano){
        switch(nombreDeLaMano){
            case "carta"
        }
    }*/

    public ManoDePoker definirManoDePoker(ArrayList<Carta> arrayDeCartas){
        this.cartas = arrayDeCartas;
        this.cantidadDeCartas = this.cartas.size();
        if(esEscalera()){
            return definirEscalera();
        }
        else{
            return definirManoComparable();
        }

    }

    private ManoDePoker definirManoComparable(){
        int cantidadDeRepetidas = 0;
        if(this.mismoColor()){
            return this.manosDePoker.get(1);
        }
        for(int indice = 0; indice < this.cantidadDeCartas; indice ++){
            int repeticiones = Collections.frequency(this.cartas,this.cartas.get(indice));
            if(repeticiones >= 2){ //necesito que se dos cartas sean iguales si su valor (ej: uno,k) es el mismo
                cantidadDeRepetidas ++ ;
            }
        }
        switch(cantidadDeRepetidas){
            case 0:
                return this.manosDePoker.get(0);//CartaMasAlta();

            case 2:
                return this.manosDePoker.get(7); //Par();

            case 3:
                return this.manosDePoker.get(9); //Trio();

            case 4://veo si es doble par o poker
                return this.definidorDeCuatroRepetidas();

            case 5:
                return this.manosDePoker.get(6); //FullHouse();
        }
        //si no es ninguna de las anterirores debe ser color
        return new Color();
    }


    private boolean esEscalera(){
        if(this.cantidadDeCartas < 5) return false;
        Carta cartaAnterior = this.cartas.get(0);
        Carta cartaActual;
        for(int indice = 1; indice < this.cantidadDeCartas ; indice++){
            cartaActual = this.cartas.get(indice);
            if( ! cartaAnterior.esAntecesor(cartaActual)){
                return false;
            }
            cartaAnterior = cartaActual;
        }
        return true;
    }
    /*la carta puede tener un atributo "antecesor" para poder saber si la carta que tengo ahora
    le sigue a la carta que tenía antes solo preguntando si la antecesora es la carta ya obtenida
    y responde con un booleano
     */

    private ManoDePoker definirEscalera(){
        if(this.cartas.get(0).equals(this.as)){ //nos dice si la primer carta es un as
            return this.manosDePoker.get(4); //EscaleraReal();
        }else if(this.mismoColor()){
            return this.manosDePoker.get(3); //EscaleraColor();
        }
        return this.manosDePoker.get(5); //EscaleraSimple();
    }

    private ManoDePoker definidorDeCuatroRepetidas(){
        int repeticiones = 0;
        for(int indice = 0; indice < 2; indice++){
            repeticiones = Collections.frequency(this.cartas,this.cartas.get(indice));
            if(repeticiones == 2){ return this.manosDePoker.get(2);} //DoblePar(); }
            else if (repeticiones == 4) { return this.manosDePoker.get(8);} //Poker(); }
        }
        return new Poker();
    }

    private boolean mismoColor(){
        if(this.cantidadDeCartas < 5) {return false;}
        Carta cartaComparadora = this.cartas.get(0);
        for(int indice = 1; indice < this.cantidadDeCartas; indice ++){
            if(!this.cartas.get(indice).sonMismoPalo(cartaComparadora)){
                return false;
            }
        }
        return true;
    }

    public void aplicarTarotAMano(Tarot tarot){
        for (ManoDePoker manoDePoker : this.manosDePoker){
            if(tarot.puedeModificar(manoDePoker)){
                tarot.aplicarA(manoDePoker);
            }
        }

    }

}