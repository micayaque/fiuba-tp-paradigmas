package edu.fiuba.algo3.modelo.Palo;

public class Corazon implements Palo{

    public Corazon(){
    }


    @Override
    public boolean sonMismoPalo(Palo otroPalo) {
        return otroPalo instanceof Corazon;
    }

}
