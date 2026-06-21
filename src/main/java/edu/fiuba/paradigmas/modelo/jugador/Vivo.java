package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.AccionJugador;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorVivoExcepcion;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.List;

public class Vivo implements Estado {

    @Override
    public void estaVivo(Jugador jugador, List<Jugador> vivos) {
        vivos.add(jugador);
    }

    @Override
    public void procesarAccion(AccionJugador comando) {
        comando.ejecutar();
    }

    @Override
    public void morir(Jugador jugador) {
        jugador.cambiarEstado(new Muerto());
    }

    @Override
    public Rol revelarCarta(Jugador jugador) {
        throw new JugadorVivoExcepcion("No se puede revelar la carta de un jugador vivo.");
    }

    @Override
    public void serProtegido(Jugador jugador) {
        jugador.cambiarEstado(new Protegido());
    }
}