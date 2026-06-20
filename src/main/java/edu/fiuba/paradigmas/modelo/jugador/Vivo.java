package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorVivoExcepcion;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.List;

public class Vivo extends Estado {

    @Override
    public void morir(Jugador jugador) {
        jugador.cambiarEstado(new Muerto());
    }

    @Override
    public void estaVivo(Jugador jugador, List<Jugador> vivos) {
        vivos.add(jugador);
    }

    @Override
    public void intentarVotarComoMafiosoA(Jugador votante, Jugador victimaElegida, Urna urnaDeMafia) {
        votante.continuarVotacionMafiosaConCarta(victimaElegida, urnaDeMafia);
    }

    @Override
    public void recibirVotoMafioso(Jugador victima, Voto voto, Urna urna) {
        victima.continuarRecibiendoVotoMafioso(voto, urna);
    }
    
    @Override
    public void vistoPorMafia(Jugador jugador, List<Jugador> complices) {
        jugador.continuarVistoPorMafiaConCarta(complices);
    }

    @Override
    public void serProtegido(Jugador jugador) {
        jugador.cambiarEstado(new Protegido());
    }

    @Override
    public Bando intentarInvestigarA(Jugador detective, Jugador sospechoso) {
        return detective.continuarInvestigacionA(sospechoso);
    }

    @Override
    public Bando recibirInvestigacion(Jugador sospechoso) {
        return sospechoso.continuarRevelandoIdentidad();
    }


    @Override
    public void intentarVotarA(Jugador votante, Jugador candidato, Urna votacion) {
        votante.continuarVotacionA(candidato, votacion);
    }

    @Override
    public void intentarRecibirVotacionDe(Jugador candidato, Urna votacion) {
        candidato.continuarRecibiendoVotacionDe(votacion);
    }

    @Override
    public void intentarProtegerA(Jugador medico, Jugador protegido) {
        medico.continuarProteccionA(protegido);
    }

    @Override
    public Rol revelarCarta(Jugador jugador) {
        throw new JugadorVivoExcepcion("No se puede revelar la carta de un jugador vivo.");
    }
}
