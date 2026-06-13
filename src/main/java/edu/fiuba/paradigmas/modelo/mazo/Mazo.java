package edu.fiuba.paradigmas.modelo.mazo;

import edu.fiuba.paradigmas.modelo.excepciones.*;
import edu.fiuba.paradigmas.modelo.rol.*;

import java.util.List;

public class Mazo {
    public List<Rol> generarPara(List<Rol> rolesElegidos) {
        ContadorDeRoles contador = new ContadorDeRoles();
        for(Rol rol: rolesElegidos) rol.contarseEn(contador);

        int cantidadCiudadanosElegidos = contador.cantidadCiudadanos();
        int cantidadMafiososElegidos = contador.cantidadMafiosos();
        int cantidadDetectivesElegidos = contador.cantidadDetectives();
        int cantidadPadrinosElegidos = contador.cantidadPadrinos();
        int cantidadSheriffsElegidos = contador.cantidadSheriffs();
        int cantidadMedicosElegidos = contador.cantidadMedicos();
        int cantidadRolesElegida = rolesElegidos.size();
        int cantidadBandoCiudadanoElegidos = cantidadCiudadanosElegidos + cantidadDetectivesElegidos + cantidadMedicosElegidos + cantidadSheriffsElegidos;
        int cantidadBandoMafiososElegidos = cantidadMafiososElegidos + cantidadPadrinosElegidos;

        if(cantidadBandoMafiososElegidos < 1) throw new CantidadMafiososEsCeroExcepcion("Al menos debe elegirse un jugador de la mafia");
        if(cantidadRolesElegida < 5 || cantidadRolesElegida > 12) throw new CantidadRolesInvalidaExcepcion("La cantidad de roles para los jugadores debe estar entre 5 y 12");
        if(cantidadPadrinosElegidos > 1) throw new CantidadRolesEspecialesExcedidaExcepcion("Sólo se puede tener 1 padrino por partida");
        if(cantidadDetectivesElegidos > 1) throw new CantidadRolesEspecialesExcedidaExcepcion("Sólo se puede tener 1 detective por partida");
        if(cantidadSheriffsElegidos > 1) throw new CantidadRolesEspecialesExcedidaExcepcion("Sólo se puede tener 1 sheriff por partida");
        if(cantidadMedicosElegidos > 1) throw new CantidadRolesEspecialesExcedidaExcepcion("Sólo se puede tener 1 médico por partida");
        if(cantidadMafiososElegidos >= cantidadBandoCiudadanoElegidos) throw new CantidadMafiososExcedidaExcepcion("La cantidad de mafiosos debe ser menor que la cantidad de ciudadanos en el juego");

        int cantidadRolesEspeciales = cantidadDetectivesElegidos +  cantidadPadrinosElegidos + cantidadSheriffsElegidos + cantidadMedicosElegidos;
        if(cantidadRolesElegida < 7 && cantidadRolesEspeciales > 1) throw new CantidadRolesEspecialesExcedidaExcepcion("Debe haber 1 sólo rol especial como máximo para menos de 8 jugadores");
        else if(cantidadRolesElegida < 10 && cantidadRolesEspeciales > 2) throw new CantidadRolesEspecialesExcedidaExcepcion("Deben haber como máximo 2 roles especiales para menos de 10 jugadores");

        return rolesElegidos;
    }
}
