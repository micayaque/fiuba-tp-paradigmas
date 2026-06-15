package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Padrino extends Mafioso {
    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarPadrino();
    }

    @Override
    public void desempatarVotacionMafia(Jugador victima) {
        victima.morir();
    }
}
