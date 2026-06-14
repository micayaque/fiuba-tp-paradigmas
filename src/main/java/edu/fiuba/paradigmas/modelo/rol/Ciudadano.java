package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;

public class Ciudadano extends Rol {

    public Ciudadano(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarCiudadano();
    }

}