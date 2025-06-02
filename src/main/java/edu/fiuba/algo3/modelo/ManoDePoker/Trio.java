package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class Trio extends ManoDePoker{
    public Trio(){
        this.nombre = "trio";
        this.puntaje = new Puntaje(30,3);
        this.probabilidad = 2.1128;
    }
}
