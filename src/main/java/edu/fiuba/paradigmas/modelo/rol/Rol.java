package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.excepciones.MedicoImpostorExcepcion;
import edu.fiuba.paradigmas.modelo.fase.urna.Urna;

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

    public void protegerComoMedico(Jugador protegido) {
        throw new MedicoImpostorExcepcion("Un rol que no es médico intentó proteger a un jugador.");
    }

    public void votarComoMafiosoA(Jugador victima, Urna urna) {
        throw new MedicoImpostorExcepcion("Un rol que no es mafioso intentó votar a un jugador.");
    }
}