package edu.fiuba.algo3.modelo.activacion;
import edu.fiuba.algo3.modelo.ManoDePoker.ManoDePoker;

public class ActivacionSiempre implements Activacion {
    @Override
    public boolean esActivable(ManoDePoker mano) {
        return true;
    }
}