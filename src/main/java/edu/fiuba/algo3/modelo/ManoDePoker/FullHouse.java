package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class FullHouse extends ManoDePoker {
    public FullHouse(){
        this.nombre = "full";
        this.puntaje = new Puntaje(40,4);
        this.probabilidad = 0.1440;
    }
}
