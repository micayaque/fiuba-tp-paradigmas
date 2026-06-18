package edu.fiuba.paradigmas.modelo.fasediurna;

import edu.fiuba.paradigmas.modelo.excepciones.VotacionNoIniciadaExcepcion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Nominacion implements EstadoVotacionDiurna {
    @Override
    public void recibirVoto(Jugador votante, Jugador votado, Urna urna) {
        throw new VotacionNoIniciadaExcepcion("Aún no se terminó la etapa de nominación. No se puede votar.");
    }
}