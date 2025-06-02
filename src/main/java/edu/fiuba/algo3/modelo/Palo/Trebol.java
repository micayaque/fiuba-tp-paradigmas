package edu.fiuba.algo3.modelo.Palo;

public class Trebol implements Palo {

    public Trebol(){}

    @Override
    public boolean sonMismoPalo(Palo otroPalo) {
        return otroPalo instanceof Trebol;
    }
}
