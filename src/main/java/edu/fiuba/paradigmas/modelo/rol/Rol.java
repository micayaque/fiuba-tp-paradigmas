package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.excepciones.rol.RolImpostorExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.partida.VerificacionVictoria;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.voto.Voto;

import java.util.List;

public abstract class Rol {

    private final Bando bando;

    public Rol(Bando bando) {
        this.bando = bando;
    }

    public abstract void contarseEn(CreadorDeJugadores contador);

    public void puedeConocerElRolDe(Jugador otroJugador, List<Jugador> complices) {
        this.bando.intentarVerA(otroJugador, complices);
    }

    public abstract void identificarseEn(IdentificadorRol identificador);

    public void vistoPorMafia(Jugador duenio, List<Jugador> complices) {
        this.bando.vistoPorMafia(duenio, complices);
    }

    public void protegerComoMedico(Jugador protegido) {
        throw new RolImpostorExcepcion("Un rol que no es médico intentó proteger a un jugador.");
    }

    public void votarComoMafiosoA(Jugador victima, Urna urnaVotacion) {
        throw new RolImpostorExcepcion("Un rol que no es mafioso intentó votar a un jugador durante la fase nocturna.");
    }

    public Bando investigarComoDetectiveA(Jugador sospechoso) {
        throw new RolImpostorExcepcion("Un rol que no es detective intentó iniciar una investigación.");
    }

    public void revelarComoSheriff() {
        throw new RolImpostorExcepcion("Un rol que no es sheriff intentó revelarse como Sheriff.");
    }

    public void agregarComoObjetivoPrioritario(Jugador jugador, List<Jugador> objetivos) {
    }

    public Bando revelarBando() {
        return this.bando;
    }

    public void recibirVotoMafioso(Voto voto, Urna urnaVotacion) {
        this.bando.recibirVotoMafioso(voto, urnaVotacion);
    }

    public void contarBandoEn(VerificacionVictoria recuento) {
        this.bando.contarseEn(recuento);
    }
}