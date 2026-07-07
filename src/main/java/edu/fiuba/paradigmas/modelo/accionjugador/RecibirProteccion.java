package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.jugador.Protegido;

public class RecibirProteccion extends AccionJugador {
    Jugador protegido;

    public RecibirProteccion(Jugador protegido) {
        this.protegido = protegido;
    }

    @Override
    public void enVivo() {
        this.protegido.cambiarEstado(new Protegido());
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("No se puede proteger a un jugador eliminado");
    }
}
