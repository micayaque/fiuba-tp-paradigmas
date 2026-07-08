package edu.fiuba.paradigmas.modelo.rol;

import edu.fiuba.paradigmas.modelo.bando.Ciudadanos;
import edu.fiuba.paradigmas.modelo.creadordejugadores.CreadorDeJugadores;

public class Ciudadano extends Rol {

    public Ciudadano(){
        super(new Ciudadanos());
    }

    @Override
    public void contarseEn(CreadorDeJugadores contador) {
        contador.sumarCiudadano();
    }

    @Override
    public void identificarseEn(IdentificadorRol identificador) {
        identificador.esCiudadano();
    }

}