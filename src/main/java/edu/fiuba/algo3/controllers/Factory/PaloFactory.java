package edu.fiuba.algo3.controllers.Factory;

import edu.fiuba.algo3.controllers.Parser.ErrorAlCrearPalo;
import edu.fiuba.algo3.modelo.Palo.*;

public class PaloFactory {
    public static Palo crearPalo(String nombre) {
        switch (nombre.toLowerCase()) {
            case "trebol": return new Trebol();
            case "corazones": return new Corazon();
            case "diamantes": return new Diamante();
            case "picas": return new Pica();
            default: throw new ErrorAlCrearPalo();
        }
    }
}
