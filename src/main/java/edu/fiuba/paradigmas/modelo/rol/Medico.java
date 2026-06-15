package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;

public class Medico extends Rol {

    public Medico(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarMedico();
    }

    @Override
    public void protegerComoMedico(Jugador protegido) {
        protegido.serProtegido();
    }

}
