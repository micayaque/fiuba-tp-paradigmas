package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
 import edu.fiuba.paradigmas.modelo.excepciones.rol.ProteccionRepetidaExcepcion;
import edu.fiuba.paradigmas.modelo.jugador.Jugador;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;

public class Medico extends Rol {

     private Jugador ultimoProtegido;

    public Medico(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ValidadorDeComposicionDelMazo contador) {
        contador.sumarMedico();
    }

    @Override
    public String nombre() {
        return "Médico";
    }

    @Override
    public void protegerComoMedico(Jugador protegido) {
         if (protegido == this.ultimoProtegido) {
             throw new ProteccionRepetidaExcepcion("El Médico no puede proteger al mismo jugador dos noches consecutivas.");
         }
         protegido.recibirProteccion();
         this.ultimoProtegido = protegido;
    }
}
