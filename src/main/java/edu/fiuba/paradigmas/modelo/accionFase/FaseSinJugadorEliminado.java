package edu.fiuba.paradigmas.modelo.accionFase;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeFaseTranquila;

public class FaseSinJugadorEliminado implements AccionFase {

    @Override
    public void ejecutar(Fase fase) {
    }

    @Override
    public Memento guardarEstado() {
        return new MementoDeFaseTranquila();
    }

}