package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
 import edu.fiuba.paradigmas.modelo.excepciones.rol.ProteccionRepetidaExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.mazo.ContadorDeRoles;

public class Medico extends Rol {

     private Jugador ultimoProtegido;

    public Medico(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ContadorDeRoles contador) {
        contador.sumarMedico();
    }

    @Override
    public void protegerComoMedico(Jugador protegido) {
         if (protegido == this.ultimoProtegido) {
             throw new ProteccionRepetidaExcepcion("El Médico no puede proteger al mismo jugador dos noches consecutivas.");
         }
         this.ultimoProtegido = protegido;
         protegido.serProtegido();
    }
}
