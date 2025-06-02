package edu.fiuba.algo3.modelo.ManoDePoker;


import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class CartaMasAlta extends ManoDePoker{

    public CartaMasAlta(){
        this.nombre = "carta mas alta";
        this.puntaje = new Puntaje(5,1);
        this.probabilidad = 50.1177;
    }

}
