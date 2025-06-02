package edu.fiuba.algo3.modelo.activacion;
import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;


public class ActivacionDescarte implements Activacion {
    @Override
    public boolean esActivable(ManoDePoker mano) {
        return /*mano.huboDescartes()*/ true ;
    }
}
