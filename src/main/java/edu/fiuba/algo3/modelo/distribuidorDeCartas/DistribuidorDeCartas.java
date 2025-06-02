package edu.fiuba.algo3.modelo.distribuidorDeCartas;

import edu.fiuba.algo3.modelo.ManoDeComodines.ManoDeComodines;
import edu.fiuba.algo3.modelo.Mazo.Mazo;
import edu.fiuba.algo3.modelo.Seleccionable.Seleccionable;
import edu.fiuba.algo3.modelo.manoDeTarots.ManoDeTarots;

public class DistribuidorDeCartas {
    private ManoDeComodines comodines;
    private ManoDeTarots tarots;
    private Mazo cartas;

    public DistribuidorDeCartas(ManoDeComodines comodines, ManoDeTarots tarots, Mazo cartas){
        this.comodines = comodines;
        this.tarots = tarots;
        this.cartas = cartas;
    }

    public void seleccionar(Seleccionable carta ){
        String nombre = carta.obtenerNombre();
        switch(nombre){
            case "carta":
                cartas.guardar(carta);
            case "comodin":
                comodines.guardar(carta);
            case "tarot":
                tarots.guardar(carta);
        }

        /*chain of responsability o switch
        la carta no se puede mandar a si misma a la mano o mazo porque no lo tiene
        le pregunto que es para saber a donde mandarla, no es lo ideal pero no queda otra
         */
    }
}
