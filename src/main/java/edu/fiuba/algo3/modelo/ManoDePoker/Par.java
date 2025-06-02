package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class Par extends ManoDePoker{
    public Par(){
        this.nombre = "par";
        this.puntaje = new Puntaje(10,2);
        this.probabilidad = 42.2569;
    }
}
