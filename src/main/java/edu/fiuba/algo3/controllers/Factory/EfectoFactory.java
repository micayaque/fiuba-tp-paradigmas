package edu.fiuba.algo3.controllers.Factory;

import edu.fiuba.algo3.controllers.Parseados.EfectoParseado;
import edu.fiuba.algo3.controllers.Parser.ErrorAlParsearJson;
import edu.fiuba.algo3.modelo.Puntaje.Puntaje;
import edu.fiuba.algo3.modelo.efectos.AumentarPuntaje;
import edu.fiuba.algo3.modelo.efectos.AumentarPuntajeYMultiplicador;
import edu.fiuba.algo3.modelo.efectos.Efecto;
import edu.fiuba.algo3.modelo.efectos.MultiplicarMultiplicador;

public class EfectoFactory {
    public static Efecto crearEfecto(EfectoParseado efecto) {
        Efecto efectoObtenido;
        if(efecto.getPuntos() > 1){
            efectoObtenido = new AumentarPuntaje(new Puntaje(efecto.getPuntos(), 1));
        }else if(efecto.getMultiplicador() > 1){
            efectoObtenido = new MultiplicarMultiplicador(new Puntaje(1, efecto.getMultiplicador()));
        }
        else if(efecto.getPuntos() > 1 && efecto.getMultiplicador() > 1){
            efectoObtenido = new AumentarPuntajeYMultiplicador(new Puntaje(efecto.getPuntos(), efecto.getMultiplicador()));
        }else {
            throw new ErrorAlParsearJson();
        }
        return efectoObtenido;
    }
}
