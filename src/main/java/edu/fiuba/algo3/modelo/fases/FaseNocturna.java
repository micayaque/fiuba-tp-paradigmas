package edu.fiuba.algo3.modelo.fases;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.jugadores.JugadorNulo;
import edu.fiuba.algo3.modelo.roles.*;
import edu.fiuba.algo3.modelo.votacion.VotacionNocturna;
import edu.fiuba.algo3.modelo.votacion.Voto;

public class FaseNocturna implements TurnoNocturno {

    private List<Jugador> jugadores;
    private final Map<Jugador, Jugador> objetivosDeLaMafia = new HashMap<>();
    private Jugador jugadorProtegido = JugadorNulo.obtenerInstancia();

    public FaseNocturna(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public ResultadoNocturno ejecutar() {

        for (Jugador jugador : jugadores) {
            jugador.rol().aceptarAccionNocturna(jugador, this);
        }

        VotacionNocturna votacion = new VotacionNocturna();

        for (Jugador mafioso : this.objetivosDeLaMafia.keySet()) {        
            Jugador elegido = objetivosDeLaMafia.get(mafioso);
            Voto voto = new Voto(mafioso, elegido);
            votacion.registrarVoto(voto);
        }

        Jugador victimaElegida = votacion.resultadoVotacion();

        ResultadoNocturno resultado = new ResultadoNocturno(victimaElegida, jugadorProtegido);
        return resultado;
    }

    @Override
    public void pedirAccion(Jugador jugador, Mafioso mafioso) {
        objetivosDeLaMafia.put(jugador, mafioso.elegirVictima(jugadores));
    }

    @Override
    public void pedirAccion(Jugador jugador, Detective detective) {

    }

    @Override
    public void pedirAccion(Jugador jugador, Medico medico) {
        this.jugadorProtegido = medico.proteger(jugadores);
    }

    @Override
    public void pedirAccion(Jugador jugador, Rol sinAccionNocturna) {

    }
}