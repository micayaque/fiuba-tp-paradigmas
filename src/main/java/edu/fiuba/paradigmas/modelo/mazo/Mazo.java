package edu.fiuba.paradigmas.modelo.mazo;

import edu.fiuba.paradigmas.modelo.rol.*;

import java.util.List;

public class Mazo {
    public List<Rol> validar(List<Rol> rolesElegidos) {
        ContadorDeRoles contador = new ContadorDeRoles();
        for (Rol rol : rolesElegidos) {
            rol.contarseEn(contador);
        }
        contador.validar();
        return rolesElegidos;
    }
}
