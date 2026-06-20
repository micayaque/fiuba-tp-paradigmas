package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.List;

public interface Estado {
    void morir(Jugador jugador);

    void estaVivo(Jugador jugador, List<Jugador> vivos);

    void intentarVotarComoMafiosoA(Jugador votante, Jugador victimaElegida, Urna urnaDeMafia);

    void recibirVotoMafioso(Jugador victima, Voto voto, Urna urnaDeMafia);

    void vistoPorMafia(Jugador jugador, List<Jugador> complices);

    void serProtegido(Jugador jugador);

    Bando intentarInvestigarA(Jugador detective, Jugador sospechoso);

    Bando recibirInvestigacion(Jugador sospechoso);

    void intentarVotarA(Jugador votante, Jugador candidato, Urna votacion);

    void intentarRecibirVotacionDe(Jugador candidato, Urna votacion);

    void intentarProtegerA(Jugador medico, Jugador protegido);

    Rol revelarCarta(Jugador jugador);
}
