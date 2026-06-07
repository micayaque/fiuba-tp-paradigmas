package edu.fiuba.algo3.modelo.fases;

import edu.fiuba.algo3.modelo.jugadores.Jugador;
import edu.fiuba.algo3.modelo.roles.Detective;
import edu.fiuba.algo3.modelo.roles.Mafioso;
import edu.fiuba.algo3.modelo.roles.Medico;
import edu.fiuba.algo3.modelo.roles.Rol;
import edu.fiuba.algo3.modelo.votacion.VotacionNocturna;
import edu.fiuba.algo3.modelo.votacion.VotoEnBlanco;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FaseNocturna implements TurnoNocturno {

    private final AccionesNocturnas acciones;
    private final VotacionNocturna votacionDeLaMafia = new VotacionNocturna();
    private List<Jugador> vivos = new ArrayList<>();
    private Jugador protegido;

    public FaseNocturna(AccionesNocturnas acciones) {
        this.acciones = acciones;
    }

    public ResultadoNocturno ejecutar(List<Jugador> jugadores) {
        vivos = jugadores.stream().filter(Jugador::estaVivo).collect(Collectors.toList());

        for (Jugador jugador : vivos) {
            jugador.miRol().aceptarAccion(jugador, this);
        }

        Jugador victima = votacionDeLaMafia.resultadoVotacion();
        boolean huboEliminacion = victima != null && !victima.equals(protegido);
        if (huboEliminacion) {
            victima.eliminar();
        }
        return new ResultadoNocturno(victima, protegido, huboEliminacion);
    }

    @Override
    public void pedirAccion(Jugador jugador, Mafioso mafioso) {
        if (!acciones.tieneVotoDe(jugador)) {
            votacionDeLaMafia.registrarVoto(new VotoEnBlanco(jugador));
            return;
        }
        votacionDeLaMafia.registrarVoto(mafioso.elegirVictima(jugador, acciones.objetivoDe(jugador), vivos));
    }

    @Override
    public void pedirAccion(Jugador jugador, Detective detective) {
        votacionDeLaMafia.registrarVoto(new VotoEnBlanco(jugador));
        // La investigacion del Detective se incorpora en la Entrega 2.
    }

    @Override
    public void pedirAccion(Jugador jugador, Medico medico) {
        votacionDeLaMafia.registrarVoto(new VotoEnBlanco(jugador));
        this.protegido = medico.proteger(acciones.protegido(), vivos);
    }

    @Override
    public void pedirAccion(Jugador jugador, Rol sinAccionNocturna) {
        votacionDeLaMafia.registrarVoto(new VotoEnBlanco(jugador));
    }
}
