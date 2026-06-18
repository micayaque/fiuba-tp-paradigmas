package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.excepciones.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Urna;
import edu.fiuba.paradigmas.modelo.fasenocturna.urna.Voto;
import edu.fiuba.paradigmas.modelo.rol.Rol;

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
    public void recibirVotoMafioso(Jugador victima, Voto voto, Urna urnaDeMafia) {
        throw new JugadorMuertoExcepcion("La mafia intentó votar a un jugador que ya está muerto.");
    }

    @Override
    public void estaVivo(Jugador jugador, List<Jugador> vivos) {
        // Null Object
    }

    @Override
    public void intentarVotarComoMafiosoA(Jugador votante, Jugador victimaElegida, Urna urnaDeMafia) {
        throw new JugadorMuertoExcepcion("Un jugador muerto intentó votar a otro jugador.");
    }

    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
    }

    @Override
    public void serProtegido(Jugador jugador) {
        throw new JugadorMuertoExcepcion("No se puede proteger a un jugador muerto.");
    }

    @Override
    public Bando intentarInvestigarA(Jugador detective, Jugador sospechoso) {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede inverstigar.");
    }

    @Override
    public Bando recibirInvestigacion(Jugador sospechoso) {
        throw new JugadorMuertoExcepcion("No se puede investigar a un jugador muerto.");
    }

    @Override
    public void intentarVotarA(Jugador votante, Jugador candidato, Urna votacion) {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede nominar.");
    }

    @Override
    public void intentarRecibirVotacionDe(Jugador candidato, Jugador votante, Urna votacion) {
        throw new JugadorMuertoExcepcion("Un jugador muerto no puede recibir una nominación.");
    }

    @Override
    public void intentarProtegerA(Jugador medico, Jugador protegido) {
        throw new JugadorMuertoExcepcion("Un médico muerto no puede proteger a nadie.");
    }

    @Override
    public Rol revelarCarta(Jugador jugador) {
        return jugador.continuarRevelandoCarta();
    }
}