package edu.fiuba.algo3.modelo.roles;

public class Padrino extends Mafioso {

    @Override
    public Bando bandoAparente() {
        return new Ciudadanos();
    }
}
