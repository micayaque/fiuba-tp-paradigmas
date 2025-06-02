package edu.fiuba.algo3.modelo.ManoDePoker;

import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class DoblePar extends ManoDePoker{
    public DoblePar(){
        this.nombre = "doble par";
        this.puntaje = new Puntaje(20,2);
        this.probabilidad = 4.7539;
    }


}
