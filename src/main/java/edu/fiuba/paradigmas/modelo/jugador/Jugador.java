package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.*;
import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;
import edu.fiuba.paradigmas.modelo.urna.Urna;
import edu.fiuba.paradigmas.modelo.urna.UrnaDeVotacion;
import edu.fiuba.paradigmas.modelo.voto.Voto;
import edu.fiuba.paradigmas.modelo.partida.RecuentoDeBandos;
import edu.fiuba.paradigmas.modelo.rol.IdentificadorRol;
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

    public void contarseEn(CreadorDeJugadores contador) {
        carta.contarseEn(contador);
    }

    public void identificarRolEn(IdentificadorRol identificador) {
        this.carta.identificarseEn(identificador);
    }

    public void puedeConocerElRolDe(Jugador otroJugador, List<Jugador> conocidos) {
        if(this == otroJugador) {
            conocidos.add(this);
        } else {
            this.carta.puedeConocerElRolDe(otroJugador, conocidos);
        }
    }



    public void cambiarEstado(Estado nuevoEstado) {
        this.estado = nuevoEstado;
    }

    public void estaVivo(List<Jugador> vivos) {
        this.estado.estaVivo(this, vivos);
    }

    public void contarBandoEn(RecuentoDeBandos recuento) {
        this.carta.contarBandoEn(recuento);
    }

    public void revelarseComoSheriff() {
        AccionJugador comando = new RevelarseComoSheriff(this);
        this.estado.procesarAccion(comando);
    }

    public void continuarRevelandoseComoSheriff() {
        this.carta.revelarComoSheriff();
    }

    public void eliminarProteccion() {
        this.estado.eliminarProteccion(this);
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

    public void morir() {
        AccionJugador comando = new RecibirEliminacion(this);
        this.estado.procesarAccion(comando);
    }



    public void protegerA(Jugador protegido) {
        AccionJugador comando = new Proteger(this, protegido);
        this.estado.procesarAccion(comando);
    }

    public void continuarProteccionA(Jugador protegido) {
        this.carta.protegerComoMedico(protegido);
    }

    public void recibirProteccion() {
        this.estado.procesarAccion(new RecibirProteccion(this));
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
        RevelarCarta comando = new RevelarCarta(this);
        this.estado.procesarAccion(comando);
        return comando.obtenerResultado();
    }

    public Rol continuarRevelandoCarta() {
        return this.carta;
    }

    public void agregarComoObjetivoPrioritario(List<Jugador> objetivos) {
        this.carta.agregarComoObjetivoPrioritario(this, objetivos);
    }

}