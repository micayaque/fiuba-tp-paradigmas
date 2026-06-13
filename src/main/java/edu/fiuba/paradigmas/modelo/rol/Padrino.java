package edu.fiuba.paradigmas.modelo.rol;

public class Padrino extends Mafioso {
    @Override
    public void contarseEn(ContadorDeRoles c) {
        c.sumarPadrino();
    }
}
