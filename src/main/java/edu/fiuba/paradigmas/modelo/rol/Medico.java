package edu.fiuba.paradigmas.modelo.rol;

public class Medico extends Rol {
    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarMedico();
    }
}
