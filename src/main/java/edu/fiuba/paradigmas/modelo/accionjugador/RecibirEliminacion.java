package edu.fiuba.paradigmas.modelo.accionjugador;

import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.historial.Memento;
import edu.fiuba.paradigmas.modelo.historial.MementoDeEliminacion;
import edu.fiuba.paradigmas.modelo.historial.MementoDeProteccion;
import edu.fiuba.paradigmas.modelo.historial.Originador;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.jugador.Muerto;
import edu.fiuba.paradigmas.modelo.rol.Rol;

public class RecibirEliminacion extends AccionJugador implements Originador {
    private final Jugador eliminado;
    Memento resultado;

    public RecibirEliminacion(Jugador eliminado) {
        this.eliminado = eliminado;
    }

    @Override
    public void enVivo() {
        this.eliminado.cambiarEstado(new Muerto());
        Rol rolRevelado = this.eliminado.revelarCarta();
        this.resultado = new MementoDeEliminacion(this.eliminado, rolRevelado);
    }

    @Override
    public void enProtegido() {
        this.resultado = new MementoDeProteccion(this.eliminado);
    }

    @Override
    public void enMuerto() {
        throw new JugadorMuertoExcepcion("Un jugador ya eliminado no puede ser eliminado nuevamente");
    }

    @Override
    public Memento guardarEstado() {
        return this.resultado;
    }
}
