package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.mazo.ContadorDeRoles;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.List;

public class Jugador {

    private final String nombre;
    private final Rol carta;
    private Estado estado;

    public Jugador(String nombre, Rol carta) {
        this.nombre = nombre;
        this.carta = carta;
        this.estado = new Vivo();
    }

    public String nombre() {
        return nombre;
    }

    public void contarseEn(ContadorDeRoles contador) {
        carta.contarseEn(contador);
    }

    public void puedeConocerElRolDe(Jugador otroJugador, List<Jugador> conocidos) {
        if(this == otroJugador) {
            conocidos.add(this);
        } else {
            this.carta.puedeConocerElRolDe(otroJugador, conocidos);
        }
    }

    public void vistoPorMafia(List<Jugador> complices) {
        this.estado.vistoPorMafia(this, complices);
    }

    public void morir() {
        this.estado.morir(this);
    }

    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void votarComoMafiosoA(Jugador victimaElegida, Urna urnaDeMafia) {
        this.estado.intentarVotarComoMafiosoA(this, victimaElegida, urnaDeMafia);
    }

    public void continuarVotacionMafiosaConCarta(Jugador victimaElegida, Urna urnaDeMafia) {
        this.carta.votarComoMafiosoA(victimaElegida, urnaDeMafia);
    }

    public void recibirVotoMafioso(Voto voto, Urna urnaDeMafia) {
        this.estado.recibirVotoMafioso(this, voto, urnaDeMafia);    
    }

    public void protegerA(Jugador protegido) {
        this.estado.intentarProtegerA(this, protegido);
    }

    public void continuarProteccionA(Jugador protegido) {
        this.carta.protegerComoMedico(protegido);
    }

    public void serProtegido() {
        this.estado.serProtegido(this);
    }

    public void estaVivo(List<Jugador> vivos) {
        this.estado.estaVivo(this, vivos);
    }

    public void continuarVistoPorMafiaConCarta(List<Jugador> complices) {
        this.carta.vistoPorMafia(this, complices);
    }

    public Bando investigarA(Jugador sospechoso) {
        return this.estado.intentarInvestigarA(this, sospechoso);
    }

    public Bando continuarInvestigacionA(Jugador sospechoso) {
        return this.carta.investigarComoDetectiveA(sospechoso);
    }

    public Bando serInvestigado() {
        return this.estado.recibirInvestigacion(this);
    }

    public Bando continuarRevelandoIdentidad() {
        return this.carta.revelarBando();
    }

    public void votarA(Jugador nominado, Urna votacion) {
        this.estado.intentarVotarA(this, nominado, votacion);
    }

    public void continuarVotacionA(Jugador candidato, Urna votacion) {
        candidato.recibirVotacionDe(this, votacion);
    }

    public void recibirVotacionDe(Jugador votante, Urna votacion) {
        this.estado.intentarRecibirVotacionDe(this, votacion);
    }

    public void continuarRecibiendoVotacionDe(Urna votacion) {
        votacion.agregarVoto(new Voto(this));
    }

    public Rol revelarCarta() {
        return this.estado.revelarCarta(this);
    }

    public Rol continuarRevelandoCarta() {
        return this.carta.revelarCarta();
    }

    public void continuarRecibiendoVotoMafioso(Voto voto, Urna urna) {
        this.carta.recibirVotoMafioso(voto, urna);
    }
}