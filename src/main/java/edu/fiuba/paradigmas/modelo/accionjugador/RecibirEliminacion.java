package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.jugador.Muerto;

public class RecibirEliminacion extends AccionJugador {
    Jugador eliminado;

    public RecibirEliminacion(Jugador eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public void enVivo() {
        this.eliminado.cambiarEstado(new Muerto());
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("Un jugador ya eliminado no puede ser eliminado nuevamente");
    }

    @Override
    public void enProtegido() {
        // El Médico le anula el ataque: el protegido absorbe la eliminación y no muere.
    }
}
