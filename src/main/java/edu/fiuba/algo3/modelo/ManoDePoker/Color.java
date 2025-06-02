package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class Color extends ManoDePoker {
    public Color(){
        this.nombre = "color";
        this.puntaje = new Puntaje(35,4);
        this.probabilidad =  0.1965;
    }



}
