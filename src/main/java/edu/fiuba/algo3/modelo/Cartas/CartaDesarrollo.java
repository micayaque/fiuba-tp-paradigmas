package edu.fiuba.algo3.modelo.Cartas;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Tablero.Tablero;

import java.util.List;

abstract public class CartaDesarrollo {
    private IEstadoCarta estado;
    public  CartaDesarrollo() {
        this.estado = new EstadoCartaRecienComprada();
    }
    protected CartaDesarrollo(IEstadoCarta estado) {
        this.estado = estado;
    }
    public void nuevoTurno() {
        this.estado = this.estado.actualizarEstado();
    }
    public void usar() {
        this.estado.comprobarUso();
    }
    public abstract void ejecutarEfecto(Jugador jugador, Tablero tablero, List<Jugador> oponentes);
}
