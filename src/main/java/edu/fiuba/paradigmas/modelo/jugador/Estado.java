package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.fase.urna.Urna;
import edu.fiuba.paradigmas.modelo.fase.urna.Voto;

import java.util.List;

public abstract class Estado {
    public abstract void postularseComoCandidatoParaMafia(Jugador jugador, List<Jugador> opciones);

    public abstract void morir(Jugador jugador);

    public abstract void estaVivo(Jugador jugador, List<Jugador> vivos);

    public abstract void intentarVotarComoMafiosoA(Jugador votante, Jugador victimaElegida, Urna urnaDeMafia);

    public abstract void recibirVotoMafioso(Jugador victima, Voto voto, Urna urnaDeMafia);

    public abstract void vistoPorMafia(Jugador jugador, List<Jugador> complices);

    public abstract void serProtegido(Jugador jugador);

    public abstract Bando intentarInvestigarA(Jugador detective, Jugador sospechoso);

    public abstract Bando recibirInvestigacion(Jugador sospechoso);
}
