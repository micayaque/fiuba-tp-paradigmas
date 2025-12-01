package edu.fiuba.algo3.modelo.Cartas;

import edu.fiuba.algo3.modelo.Jugador;

import java.util.HashMap;
import java.util.Map;

public class GranCaballeria {

    private Jugador lider = null;
    private static final int MINIMO_NECESARIO = 3;

    public void registrarCaballeroJugado(Jugador aspirante) {
        // Tell, Don't Ask (parcial): Le pedimos el total al experto (Jugador)
        int cantidadDelAspirante = aspirante.getCantidadCaballerosUsados();

        evaluarLiderazgo(aspirante, cantidadDelAspirante);
    }

    private void evaluarLiderazgo(Jugador aspirante, int cantidadAspirante) {
        // 1. Regla base: Tener al menos 3
        if (cantidadAspirante < MINIMO_NECESARIO) return;

        // 2. Si no hay líder, tomamos el puesto
        if (lider == null) {
            asignarNuevoLider(aspirante);
            return;
        }

        // 3. Si soy el líder actual, no hago nada (ya tengo el bonus)
        if (lider.equals(aspirante)) return;

        // 4. Desafío: Para robar el título, debo SUPERAR estrictamente al líder
        // Le preguntamos al líder actual cuánto tiene (Fuente de verdad viva)
        int cantidadDelLider = lider.getCantidadCaballerosUsados();

        if (cantidadAspirante > cantidadDelLider) {
            transferirLiderazgo(aspirante);
        }
    }

    private void asignarNuevoLider(Jugador nuevoLider) {
        this.lider = nuevoLider;
        nuevoLider.sumarPuntoDeVictoriaPublico(2);
    }

    private void transferirLiderazgo(Jugador nuevoLider) {
        this.lider.restarPuntoDeVictoriaPublico(2); // Quitamos al viejo
        this.asignarNuevoLider(nuevoLider);         // Damos al nuevo
    }
}
