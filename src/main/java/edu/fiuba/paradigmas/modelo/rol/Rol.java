package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.excepciones.PadrinoImpostorExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

import java.util.List;

public abstract class Rol {

    private final Bando bando;

    public Rol(Bando bando) {
        this.bando = bando;
    }

    public abstract void contarseEn(ContadorDeRoles contador);

    public void puedeConocerElRolDe(Jugador otroJugador, List<Jugador> complices) {
        this.bando.intentarVerA(otroJugador, complices);
    }

    public void vistoPorMafia(Jugador duenio, List<Jugador> complices) {
        this.bando.vistoPorMafia(duenio, complices);
    }

    public void validarBandoYPostularseComoCandidatoParaMafia(Jugador jugador, List<Jugador> opciones) {
        this.bando.postularseComoCandidatoParaMafia(jugador, opciones);
    }

    public void desempatarVotacionMafia(Jugador victima) {
        throw new PadrinoImpostorExcepcion("Un jugador que no es Padrino intentó desempatar la votación.");
    }
}