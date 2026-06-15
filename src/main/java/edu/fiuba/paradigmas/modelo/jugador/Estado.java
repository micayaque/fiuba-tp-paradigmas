package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.fase.Urna;

import java.util.List;

public abstract class Estado {
    public abstract void postularseComoCandidatoParaMafia(Jugador jugador, List<Jugador> opciones);

    public abstract void recibirVotoMafioso(Jugador jugador, Urna urnaDeMafia);

    public abstract void morir(Jugador jugador);

    public abstract void estaVivo(Jugador jugador, List<Jugador> vivos);

    public abstract void intentarVotarComoMafiosoA(Jugador victimaElegida, Urna urnaDeMafia);

    public abstract void vistoPorMafia(Jugador jugador, List<Jugador> complices);

    public abstract void desempatarVotacionMafia(Jugador jugador, Jugador victima);

    public abstract void serProtegido(Jugador jugador);
}
