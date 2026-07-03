package edu.fiuba.paradigmas.modelo.partida;

import edu.fiuba.paradigmas.modelo.fase.Fase;

public class PartidaEnCurso implements ResultadoPartida {

    @Override
    public void ejecutar(Fase fase, Moderador moderador) {
        fase.avanzar(moderador);
    }
}
