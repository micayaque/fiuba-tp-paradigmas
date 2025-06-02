package edu.fiuba.algo3.modelo.Palo;

public class Pica implements Palo {
    public Pica(){};
    @Override
    public boolean sonMismoPalo(Palo otroPalo) {
        return otroPalo instanceof Pica;
    }
}
