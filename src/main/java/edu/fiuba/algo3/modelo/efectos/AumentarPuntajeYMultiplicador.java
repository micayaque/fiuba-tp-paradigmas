package edu.fiuba.algo3.modelo.efectos;

import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public class AumentarPuntajeYMultiplicador extends Efecto{

    public AumentarPuntajeYMultiplicador(Puntaje puntaje) {super(puntaje);}


    @Override
    public Puntaje aplicarEfecto(Puntaje puntaje) {
        AumentarPuntaje aumentarPuntaje = new AumentarPuntaje(this.puntaje);
        MultiplicarMultiplicador multiplicador = new MultiplicarMultiplicador(this.puntaje);

        puntaje = aumentarPuntaje.aplicarEfecto(puntaje);
        puntaje = multiplicador.aplicarEfecto(puntaje);

        return puntaje;
    }
}
