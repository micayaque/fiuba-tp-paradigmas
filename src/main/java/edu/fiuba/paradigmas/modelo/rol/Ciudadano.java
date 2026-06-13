package edu.fiuba.paradigmas.modelo.rol;

public class Ciudadano extends Rol {
    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarCiudadano();
    }
}
