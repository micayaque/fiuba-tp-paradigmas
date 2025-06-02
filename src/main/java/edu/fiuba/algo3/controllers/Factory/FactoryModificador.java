package edu.fiuba.algo3.controllers.Factory;

import edu.fiuba.algo3.modelo.Modificable.Modificador;

public class FactoryModificador {

    public static Modificador generarModificador(String sobre, String ejemplar) {
        Modificador modificador;
        if (sobre.equals("carta")) {
             modificador = new Modificador(sobre);
        } else {
            modificador = new Modificador(ejemplar);
        }
        return modificador;
    }
}
