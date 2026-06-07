package edu.fiuba.algo3.modelo.roles;

public abstract class Bando {

    @Override
    public boolean equals(Object otro) {
        return otro != null && getClass() == otro.getClass();
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
