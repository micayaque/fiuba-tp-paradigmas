package edu.fiuba.algo3.modelo.roles;

public class Detective extends Rol {

    @Override
    public Bando bando() {
        return new Ciudadanos();
    }
}
