package edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class PrimeraVotacion extends EstadoVotacionDiurna {

    @Override
    protected void validarCandidato(Jugador votado) {
        // En la primera votación cualquier jugador vivo es candidato: no impone restricción.
    }
}
