package edu.fiuba.algo3.modelo.efectos;

import edu.fiuba.algo3.controllers.Parseados.EfectoParseado;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public abstract class Efecto {
    protected Puntaje puntaje;

    public Efecto(Puntaje puntaje){
       this.puntaje = puntaje;
    }

    public abstract Puntaje aplicarEfecto(Puntaje puntaje);
}
