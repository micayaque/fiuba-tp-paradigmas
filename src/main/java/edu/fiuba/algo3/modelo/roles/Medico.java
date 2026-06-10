package edu.fiuba.algo3.modelo.roles;

public class Medico extends Rol {

    @Override
    public Bando bando() {
        return new Ciudadanos();
    }

    // La restriccion de no proteger al mismo jugador dos noches seguidas
    // se incorpora en la Entrega 2 (el Medico recordara su ultima proteccion).
}
