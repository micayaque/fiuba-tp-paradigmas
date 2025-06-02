package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class Poker extends ManoDePoker{
    public Poker(){
        this.nombre = "poker";
        this.puntaje = new Puntaje(60,7);
        this.probabilidad = 0.0240;
    }
}
