package edu.fiuba.algo3.modelo.roles;

import edu.fiuba.algo3.modelo.excepciones.VictimaInvalida;
import edu.fiuba.algo3.modelo.fases.TurnoNocturno;
import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.votacion.Voto;

import java.util.List;

public class Mafioso extends Rol {

    @Override
    public Bando bando() {
        return new Mafia();
    }

    @Override
    public boolean conoceElRolDe(Rol otro) {
        return otro.esReconocidoPorMafia();
    }

    @Override
    public boolean esReconocidoPorMafia() {
        return true;
    }

    @Override
    public void aceptarAccionNocturna(Jugador propio, TurnoNocturno turno) {
        turno.pedirAccion(propio, this);
    }

    public Jugador elegirVictima(List<Jugador> jugadores) {
        Jugador objetivo = jugadores.get(0);

        if (!objetivo.estaVivo()) {
            throw new VictimaInvalida("La victima debe ser un jugador vivo");
        }

        if (objetivo.rol().bando().esMafioso()) {
            throw new VictimaInvalida("La Mafia no puede elegir a otro miembro de la Mafia");
        }

        return objetivo;
    }
}