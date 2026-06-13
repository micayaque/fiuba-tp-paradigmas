package edu.fiuba.paradigmas.modelo.rol;

public class Mafioso extends Rol {
    @Override
    public void contarseEn(ContadorDeRoles c) {
        c.sumarMafioso();
    }
}
