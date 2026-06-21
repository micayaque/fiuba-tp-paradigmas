package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.*;
import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.Voto;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;
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

    public void contarseEn(ValidadorDeComposicionDelMazo contador) {
        carta.contarseEn(contador);
    }

    public void puedeConocerElRolDe(Jugador otroJugador, List<Jugador> conocidos) {
        if(this == otroJugador) {
            conocidos.add(this);
        } else {
            this.carta.puedeConocerElRolDe(otroJugador, conocidos);
        }
    }

    public void morir() {
        this.estado.morir(this);
    }

    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void continuarVotacionMafiosaConCarta(Jugador victimaElegida, Urna urnaDeMafia) {
        this.carta.votarComoMafiosoA(victimaElegida, urnaDeMafia);
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

    public Bando continuarInvestigacionA(Jugador sospechoso) {
        return this.carta.investigarComoDetectiveA(sospechoso);
    }

    public Bando continuarRevelandoIdentidad() {
        return this.carta.revelarBando();
    }

    public void continuarVotacionA(Jugador candidato, Urna votacion) {
        candidato.recibirVotacionDe(this, votacion);
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

    public void protegerA(Jugador protegido) {
        AccionJugador comando = new Proteger(this, protegido);
        this.estado.procesarAccion(comando);
    }

    public void votarComoCiudadano(Jugador votado, Urna votacion) {
        AccionJugador comando = new VotarComoCiudadano(this, votado, votacion);
        this.estado.procesarAccion(comando);
    }

    public void votarComoMafiosoA(Jugador victimaElegida, Urna urnaDeMafia) {
        AccionJugador comando = new VotarComoMafioso(this, victimaElegida, urnaDeMafia);
        this.estado.procesarAccion(comando);
    }

    public Bando investigarA(Jugador sospechoso) {
        Investigar comando = new Investigar(this, sospechoso);
        this.estado.procesarAccion(comando);
        return comando.obtenerResultado();
    }

    public void recibirVotoMafioso(Voto voto, Urna urnaDeMafia) {
        AccionJugador comando = new RecibirVotoNocturno(this, voto, urnaDeMafia);
        this.estado.procesarAccion(comando);
    }

    public void recibirVotacionDe(Jugador votante, Urna votacion) {
        AccionJugador comando = new RecibirVotoDiurno(this, votacion);
        this.estado.procesarAccion(comando);
    }

    public void vistoPorMafia(List<Jugador> complices) {
        this.carta.vistoPorMafia(this, complices);
    }

    public Bando serInvestigado() {
        RecibirInvestigacion comando = new RecibirInvestigacion(this);
        this.estado.procesarAccion(comando);
        return comando.obtenerResultado();
    }

}