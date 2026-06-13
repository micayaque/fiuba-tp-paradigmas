package edu.fiuba.paradigmas.modelo.rol;

public class Detective extends Rol {
    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarDetective();
    }
}