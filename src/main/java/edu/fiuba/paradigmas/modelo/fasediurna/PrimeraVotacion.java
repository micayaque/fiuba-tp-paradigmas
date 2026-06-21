package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.excepciones.fase.VotoInvalidoExcepcion;
import edu.fiuba.paradigmas.modelo.votacion.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public class PrimeraVotacion implements EstadoVotacionDiurna {
    private final List<Jugador> nominados;

    public PrimeraVotacion(List<Jugador> nominados) {
        this.nominados = nominados;
    }

    @Override
    public void recibirVoto(Jugador votante, Jugador votado, Urna urnaVotacion) {
        if (!this.nominados.contains(votado)) {
            throw new VotoInvalidoExcepcion("Solo se puede votar a jugadores nominados.");
        }
        votante.votarComoCiudadano(votado, urnaVotacion);
    }
}