package edu.fiuba.algo3.modelo.efectos;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class MultiplicarMultiplicador extends Efecto{
    public MultiplicarMultiplicador(Puntaje puntaje) {
        super(puntaje);
    }

    @Override
    public Puntaje aplicarEfecto(Puntaje puntaje) {
        puntaje.multiplicarMultiplicador(this.puntaje);
        return puntaje;
    }


}
