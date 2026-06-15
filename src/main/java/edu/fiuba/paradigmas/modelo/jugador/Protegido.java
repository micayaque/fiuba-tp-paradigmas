package edu.fiuba.paradigmas.modelo.jugador;

public class Protegido extends Vivo {

    // Se comporta como Vivo, pero el Médico le anula un ataque: absorbe un morir() y vuelve a estar Vivo.
    @Override
    public void morir(Jugador jugador) {
        jugador.cambiarEstado(new Vivo());
    }

}
