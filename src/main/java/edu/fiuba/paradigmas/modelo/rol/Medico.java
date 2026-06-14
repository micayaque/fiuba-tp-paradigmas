package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;

public class Medico extends Rol {

    public Medico(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarMedico();
    }

}