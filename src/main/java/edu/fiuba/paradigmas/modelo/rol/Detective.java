package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;

public class Detective extends Rol {

    public Detective(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarDetective();
    }

}