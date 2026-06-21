package edu.fiuba.paradigmas.modelo.jugador;

import edu.fiuba.paradigmas.modelo.accionjugador.AccionJugador;

public class Protegido extends Vivo {

    // Se comporta como Vivo, pero el Médico le anula un ataque: absorbe la acción de eliminar al jugador para no cambiar su estado a Muerto()
    @Override
    public void procesarAccion(AccionJugador comando) {
        // Null Object
    }

}
