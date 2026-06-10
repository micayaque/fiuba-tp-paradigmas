package edu.fiuba.algo3.modelo.fases;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Detective;
import edu.fiuba.algo3.modelo.roles.Mafioso;
import edu.fiuba.algo3.modelo.roles.Medico;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.votacion.VotacionNocturna;
import edu.fiuba.algo3.modelo.votacion.VotoEnBlanco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaseNocturna implements TurnoNocturno {

    // Intenciones registradas antes de resolver la noche (entran desde afuera).
    private final Map<Jugador, Jugador> objetivosDeLaMafia = new HashMap<>();
    private Jugador protegidoElegido;

    // Estado de la resolucion.
    private final VotacionNocturna votacionDeLaMafia = new VotacionNocturna();
    private Jugador protegido;

    public FaseNocturna laMafiaVotaA(Jugador mafioso, Jugador objetivo) {
        objetivosDeLaMafia.put(mafioso, objetivo);
        return this;
    }

    public FaseNocturna elMedicoProtegeA(Jugador objetivo) {
        this.protegidoElegido = objetivo;
        return this;
    }

    public ResultadoNocturno ejecutar(List<Jugador> jugadores) {
        for (Jugador jugador : jugadores) {
            if (jugador.estaVivo()) {
                jugador.miRol().aceptarAccion(jugador, this);
            }
        }
        ResultadoNocturno resultado =
                new ResultadoNocturno(votacionDeLaMafia.resultadoVotacion(), protegido);
        resultado.aplicar();
        return resultado;
    }

    @Override
    public void pedirAccion(Jugador jugador, Mafioso mafioso) {
        if (!objetivosDeLaMafia.containsKey(jugador)) {
            votacionDeLaMafia.registrarVoto(new VotoEnBlanco(jugador));
            return;
        }
        votacionDeLaMafia.registrarVoto(
                mafioso.votoNocturno(jugador, objetivosDeLaMafia.get(jugador)));
    }

    @Override
    public void pedirAccion(Jugador jugador, Medico medico) {
        this.protegido = protegidoElegido;
    }

    @Override
    public void pedirAccion(Jugador jugador, Detective detective) {
        // La investigacion del Detective se incorpora en la Entrega 2.
    }

    @Override
    public void pedirAccion(Jugador jugador, Rol sinAccionNocturna) {
        // Un rol sin accion nocturna no participa de la noche.
    }
}
