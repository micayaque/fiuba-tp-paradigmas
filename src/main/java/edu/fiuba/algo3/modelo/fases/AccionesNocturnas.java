package edu.fiuba.algo3.modelo.fases;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

import java.util.HashMap;
import java.util.Map;

public class AccionesNocturnas {

    private final Map<Jugador, Jugador> votosDeLaMafia = new HashMap<>();
    private Jugador protegidoPorElMedico;

    public AccionesNocturnas mafiosoVotaA(Jugador mafioso, Jugador objetivo) {
        votosDeLaMafia.put(mafioso, objetivo);
        return this;
    }

    public AccionesNocturnas medicoProtegeA(Jugador objetivo) {
        this.protegidoPorElMedico = objetivo;
        return this;
    }

    public boolean tieneVotoDe(Jugador mafioso) {
        return votosDeLaMafia.containsKey(mafioso);
    }

    public Jugador objetivoDe(Jugador mafioso) {
        return votosDeLaMafia.get(mafioso);
    }

    public Jugador protegido() {
        return protegidoPorElMedico;
    }
}
