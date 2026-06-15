package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fase.Urna;

import java.util.List;

public class Muerto extends Estado {

    @Override
    public void postularseComoCandidatoParaMafia(Jugador jugador, List<Jugador> opciones) {
        // Null Object
    }

    @Override
    public void morir(Jugador jugador) {
        // Null Object
    }

    @Override
    public void recibirVotoMafioso(Jugador jugador, Urna urna) {
        throw new JugadorMuertoExcepcion("La mafia intentó votar a un jugador que ya está muerto.");
    }

    @Override
    public void estaVivo(Jugador jugador, List<Jugador> vivos) {
        // Null Object
    }

    @Override
    public void intentarVotarComoMafiosoA(Jugador jugador, Urna urna) {
        throw new JugadorMuertoExcepcion("Un jugador muerto intentó votar a otro jugador.");
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
        // Null Object o excepción
    }

    @Override
    public void desempatarVotacionMafia(Jugador jugador, Jugador victima) {
        throw new JugadorMuertoExcepcion("Un jugador muerto intentó votar a otro jugador.");
    }

    @Override
    public void serProtegido(Jugador jugador) {
        // Null Object: no se puede proteger a un jugador muerto
    }
}
