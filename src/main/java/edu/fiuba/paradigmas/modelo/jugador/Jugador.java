package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.fase.Urna;
import edu.fiuba.paradigmas.modelo.rol.ContadorDeRoles;
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
        if(this == otroJugador) conocidos.add(this);
        else this.carta.puedeConocerElRolDe(otroJugador, conocidos);
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

    public void postularseComoCandidatoParaMafia(List<Jugador> candidatosValidos) {
        this.estado.postularseComoCandidatoParaMafia(this, candidatosValidos);    }

    protected void validarBandoYPostularseComoCandidatoParaMafia(List<Jugador> opciones) {
        this.carta.validarBandoYPostularseComoCandidatoParaMafia(this, opciones);
    }

    public void votarComoVictimaA(Jugador victimaElegida, Urna urnaDeMafia) {
        this.estado.intentarVotarComoMafiosoA(victimaElegida, urnaDeMafia);
    }

    public void recibirVotoMafioso(Urna urnaDeMafia) {
        this.estado.recibirVotoMafioso(this, urnaDeMafia);
    }

    public void estaVivo(List<Jugador> vivos) {
        this.estado.estaVivo(this, vivos);
    }

    protected void continuarPostulacionConCarta(List<Jugador> opciones) {
        this.carta.validarBandoYPostularseComoCandidatoParaMafia(this, opciones);
    }

    public void continuarVistoPorMafiaConCarta(List<Jugador> complices) {
        this.carta.vistoPorMafia(this, complices);
    }

    public void desempatarVotacionMafia(Jugador victimaElegida) {
        this.estado.desempatarVotacionMafia(this, victimaElegida);
    }

    void continuarDesempateConCarta(Jugador victima) {
        this.carta.desempatarVotacionMafia(victima);
    }
}