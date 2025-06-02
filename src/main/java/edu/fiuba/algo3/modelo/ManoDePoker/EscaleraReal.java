package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class EscaleraReal extends ManoDePoker{
    public EscaleraReal(){
        this.nombre = "escalera real";
        this.puntaje = new Puntaje(100,8);
        this.probabilidad = 0.0001;
    }
}
