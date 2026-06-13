package edu.fiuba.paradigmas.modelo.rol;

public class Sheriff extends Rol{
    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarSheriff();
    }
}
