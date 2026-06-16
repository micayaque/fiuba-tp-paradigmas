package edu.fiuba.paradigmas.modelo.mazo;

import edu.fiuba.paradigmas.modelo.rol.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo {
    private void validar(List<Rol> rolesElegidos) {
        ContadorDeRoles contador = new ContadorDeRoles();
        for (Rol rol : rolesElegidos) {
            rol.contarseEn(contador);
        }
        contador.validar();
        Collections.shuffle(rolesElegidos);
    }

    private void mezclar(List<Rol> rolesElegidos) {
        Collections.shuffle(rolesElegidos);
    }

    public List<Rol> generarPara(List<Rol> rolesElegidos) {
        validar(rolesElegidos);
        mezclar(rolesElegidos);
        return rolesElegidos;
    }

}
