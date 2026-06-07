package edu.fiuba.algo3.modelo.votacion;

import edu.fiuba.algo3.modelo.jugadores.Jugador;

public class VotoEnBlanco extends Eleccion {

    public VotoEnBlanco(Jugador votante) {
        super(votante);
    }

    @Override
    public void registrarEn(Conteo conteo) {
        // Null Object: una abstencion no suma votos a ningun jugador.
    }
}
