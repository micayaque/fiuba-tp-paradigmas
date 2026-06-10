package edu.fiuba.algo3.modelo.roles;

public class Detective extends Rol {

    @Override
    public Bando bando() {
        return new Ciudadanos();
    }

    // La investigacion (con bandoAparente del investigado) se incorpora en la Entrega 2.
}
