package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class EscaleraSimple extends ManoDePoker{
    public EscaleraSimple(){
        this.nombre = "escalera";
        this.puntaje = new Puntaje(30,4);
        this.probabilidad = 0.3924;
    }
}
