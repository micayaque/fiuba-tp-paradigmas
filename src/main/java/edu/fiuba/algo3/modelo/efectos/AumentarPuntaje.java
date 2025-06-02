package edu.fiuba.algo3.modelo.efectos;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class AumentarPuntaje extends Efecto{

    public AumentarPuntaje(Puntaje puntaje) {super(puntaje);}
    @Override
    public Puntaje aplicarEfecto(Puntaje otroPuntaje) {
       otroPuntaje.sumarValorDeUnPuntaje(this.puntaje);
       return otroPuntaje;
    }
}
