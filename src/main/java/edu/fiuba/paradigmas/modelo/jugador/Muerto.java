package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.AccionJugador;
import edu.fiuba.paradigmas.modelo.bando.Bando;
import edu.fiuba.paradigmas.modelo.excepciones.estado.JugadorMuertoExcepcion;
import edu.fiuba.paradigmas.modelo.rol.Rol;

import java.util.List;

public class Muerto implements Estado {

    @Override
    public void morir(Jugador jugador) {
        // Null Object
    }

    @Override
    public void estaVivo(Jugador jugador, List<Jugador> vivos) {
        // Null Object
    }

    @Override
    public void serProtegido(Jugador jugador) {
        throw new JugadorMuertoExcepcion("No se puede proteger a un jugador muerto.");
    }

    @Override
    public Rol revelarCarta(Jugador jugador) {
        return jugador.continuarRevelandoCarta();
    }

    @Override
    public void procesarAccion(AccionJugador comando) {
        comando.rechazar();
    }
}
