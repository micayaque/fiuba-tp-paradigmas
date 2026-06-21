package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.*;
import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.votacion.Urna;
import edu.fiuba.paradigmas.modelo.votacion.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.votacion.Voto;
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

    public void estaVivo(List<Jugador> vivos) {
        this.estado.estaVivo(this, vivos);
    }

    public void vistoPorMafia(List<Jugador> complices) {
        this.carta.vistoPorMafia(this, complices);
    }



    public void votarComoMafiosoA(Jugador victimaElegida, UrnaDeVotacion urnaVotacionDeMafia) {
        AccionJugador comando = new VotarComoMafioso(this, victimaElegida, urnaVotacionDeMafia);
        this.estado.procesarAccion(comando);
    }

    public void continuarVotacionMafiosaConCarta(Jugador victimaElegida, UrnaDeVotacion urnaVotacionDeMafia) {
        this.carta.votarComoMafiosoA(victimaElegida, urnaVotacionDeMafia);
    }

    public void recibirVotoMafioso(Voto voto, UrnaDeVotacion urnaVotacionDeMafia) {
        AccionJugador comando = new RecibirVotoNocturno(this, voto, urnaVotacionDeMafia);
        this.estado.procesarAccion(comando);
    }

    public void continuarRecibiendoVotoMafioso(Voto voto, UrnaDeVotacion urnaVotacion) {
        this.carta.recibirVotoMafioso(voto, urnaVotacion);
    }

    public void votarComoCiudadano(Jugador votado, Urna votacion) {
        AccionJugador comando = new VotarComoCiudadano(this, votado, votacion);
        this.estado.procesarAccion(comando);
    }

    public void recibirVotacionDe(Jugador votante, Urna votacion) {
        AccionJugador comando = new RecibirVotoDiurno(this, votacion);
        this.estado.procesarAccion(comando);
    }



    public void protegerA(Jugador protegido) {
        AccionJugador comando = new Proteger(this, protegido);
        this.estado.procesarAccion(comando);
    }

    public void serProtegido() {
        this.estado.serProtegido(this);
    }

    public void continuarProteccionA(Jugador protegido) {
        this.carta.protegerComoMedico(protegido);
    }

    public Bando investigarA(Jugador sospechoso) {
        Investigar comando = new Investigar(this, sospechoso);
        this.estado.procesarAccion(comando);
        return comando.obtenerResultado();
    }

    public Bando serInvestigado() {
        RecibirInvestigacion comando = new RecibirInvestigacion(this);
        this.estado.procesarAccion(comando);
        return comando.obtenerResultado();
    }

    public Bando continuarInvestigacionA(Jugador sospechoso) {
        return this.carta.investigarComoDetectiveA(sospechoso);
    }

    public Bando continuarRevelandoIdentidad() {
        return this.carta.revelarBando();
    }

    public Rol revelarCarta() {
        return this.estado.revelarCarta(this);
    }

    public Rol continuarRevelandoCarta() {
        return this.carta;
    }

}