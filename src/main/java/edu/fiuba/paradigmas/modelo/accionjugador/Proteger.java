package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Proteger extends
        AccionJugador {
    private final Jugador medico;
    private final Jugador protegido;

    public Proteger(Jugador medico, Jugador protegido) {
        this.medico = medico;
        this.protegido = protegido;
    }

    @Override
    public void enVivo() {
        medico.continuarProteccionA(protegido);
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("Un médico muerto no puede proteger a nadie.");
    }
}