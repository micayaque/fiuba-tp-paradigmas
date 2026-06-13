package edu.fiuba.paradigmas.modelo.rol;

public class Mafioso extends Rol {
    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarMafioso();
    }
}
