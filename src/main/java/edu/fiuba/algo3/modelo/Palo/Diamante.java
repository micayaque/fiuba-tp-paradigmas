package edu.fiuba.algo3.modelo.Palo;

public class Diamante implements Palo {
    public Diamante() {
    }

    @Override
    public boolean sonMismoPalo(Palo otroPalo) {
        return otroPalo instanceof Diamante;
    }


}