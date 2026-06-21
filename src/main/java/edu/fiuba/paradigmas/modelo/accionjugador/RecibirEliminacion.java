package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.jugador.Muerto;

public class RecibirEliminacion implements AccionJugador {
    Jugador eliminado;

    public RecibirEliminacion(Jugador eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public void ejecutar() {
        this.eliminado.cambiarEstado(new Muerto());
    }

    @Override
    public void rechazar() {
        throw new JugadorMuertoExcepcion("Un jugador ya eliminado no puede ser eliminado nuevamente");
    }
}
