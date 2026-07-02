package edu.fiuba.paradigmas.modelo.fase.estadoVotacionDiurna;

import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class PrimeraVotacion extends EstadoVotacionDiurna {
    private final List<Jugador> nominados;

    public PrimeraVotacion(List<Jugador> nominados) {
        this.nominados = nominados;
    }

    @Override
    protected void validarCandidato(Jugador votado) {
        if (!this.nominados.contains(votado)) {
            throw new VotoInvalidoExcepcion("Solo se puede votar a jugadores nominados.");
        }
    }
}
