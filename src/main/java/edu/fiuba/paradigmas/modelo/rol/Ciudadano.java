package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.creadordejugadores.ValidadorDeComposicionDelMazo;

public class Ciudadano extends Rol {

    public Ciudadano(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(ValidadorDeComposicionDelMazo contador) {
        contador.sumarCiudadano();
    }

    @Override
    public String nombre() {
        return "Ciudadano";
    }

}