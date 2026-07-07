package edu.fiuba.paradigmas.modelo.accionFase;

import edu.fiuba.paradigmas.modelo.fase.Fase;
import edu.fiuba.paradigmas.modelo.historial.Originador;

public interface AccionFase extends Originador {
    void ejecutar(Fase fase);
}