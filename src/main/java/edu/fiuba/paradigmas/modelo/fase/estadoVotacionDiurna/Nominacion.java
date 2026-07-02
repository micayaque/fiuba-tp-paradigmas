package edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Nominacion extends EstadoVotacionDiurna {

    @Override
    protected void validarCandidato(Jugador votado) {
        // En la nominación cualquier jugador vivo es candidato: no impone restricción.
    }
}
