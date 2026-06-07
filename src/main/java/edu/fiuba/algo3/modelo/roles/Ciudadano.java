package edu.fiuba.algo3.modelo.roles;

public class Ciudadano extends Rol {

    @Override
    public Bando bando() {
        return new Ciudadanos();
    }
}
