package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Mafia;

public class Mafioso extends Rol {

    public Mafioso(){
        super(new Mafia());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarMafioso();
    }

}