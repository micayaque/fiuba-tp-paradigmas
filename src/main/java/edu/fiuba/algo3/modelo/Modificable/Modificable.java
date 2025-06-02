package edu.fiuba.algo3.modelo.Modificable;

import edu.fiuba.algo3.modelo.Modificable.Modificador;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;

public interface Modificable {
    void aplicarModificacion(Puntaje valor);
    Boolean validarModificable(Modificador modificador);

}