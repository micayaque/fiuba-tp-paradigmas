package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.excepciones.VictimaInvalida;
import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.votacion.Voto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Mafioso extends Rol {

    private Collection<Jugador> complices = new ArrayList<>();

    @Override
    public Bando bando() {
        return new Mafia();
    }

    @Override
    public void reconocerComplices(Jugador propio, Collection<Jugador> jugadores) {
        List<Jugador> otrosMafiosos = jugadores.stream()
                .filter(jugador -> jugador != propio)
                .filter(jugador -> jugador.miRol().bando().equals(new Mafia()))
                .collect(Collectors.toList());
        registrarComplices(otrosMafiosos);
    }

    public void registrarComplices(Collection<Jugador> complices) {
        this.complices = new ArrayList<>(complices);
    }

    public Collection<Jugador> complices() {
        return new ArrayList<>(complices);
    }

    @Override
    public boolean conoceElRolDe(Jugador otro) {
        return complices.contains(otro);
    }

    @Override
    public void aceptarAccion(Jugador propio, TurnoNocturno turno) {
        turno.pedirAccion(propio, this);
    }

    public Voto elegirVictima(Jugador votante, Jugador objetivo, Collection<Jugador> vivos) {
        if (objetivo == null || !vivos.contains(objetivo) || !objetivo.estaVivo()) {
            throw new VictimaInvalida("La victima debe ser un jugador vivo");
        }
        if (objetivo.miRol().bando().equals(new Mafia())) {
            throw new VictimaInvalida("La Mafia no puede elegir a otro miembro de la Mafia");
        }
        return new Voto(votante, objetivo);
    }
}
